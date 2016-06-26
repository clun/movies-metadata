package fr.clunven.docu.service;

import static fr.clunven.docu.service.BatchPredicates.sousGenreFolder;
import static fr.clunven.docu.service.BatchPredicates.validFolderDocumentaires;
import static fr.clunven.docu.service.BatchPredicates.validMovieFiles;
import static java.util.Arrays.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.ff4j.FF4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.clunven.docu.dao.db.DocumentaryDbDao;
import fr.clunven.docu.dao.db.EpisodeDbDao;
import fr.clunven.docu.dao.db.ReferentialDbDao;
import fr.clunven.docu.dao.db.SerieDbDao;
import fr.clunven.docu.dao.db.dto.GenreDto;
import fr.clunven.docu.dao.db.dto.SerieDto;
import fr.clunven.docu.domain.ComparaisonFsDb;
import fr.clunven.docu.domain.Documentaire;
import fr.clunven.docu.domain.Episode;
import fr.clunven.docu.domain.SmartFileName;
import fr.clunven.docu.domain.SmartSerieName;
import fr.clunven.docu.media.MovieMetadata;

/**
 * Documentaires services.
 * 
 * @author Cedrick Lunven (@clunven)</a>
 */
@Service("docu.batch.service")
public class BatchServices {

    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(BatchServices.class);
    
    @Autowired
    private FF4j ff4j;
    
    @Autowired
    private ReferentialDbDao refDbDao;
    
    @Autowired
    private DocumentaryDbDao docuDbDao;
    
    @Autowired
    private EpisodeDbDao episodeDbDao;
    
    @Autowired
    private SerieDbDao serieDbDao;
    
    /** Si 'true' ne modifie jamais la base de données. */
    private boolean modeSimulation = false;
    
    /**
     * Main operation of the Batch.
     *
     * @param folderPath
     *      current root folder
     * @throws Exception 
     */
    public void run(String folderPath) throws Exception {
        File rootFolder = new File(folderPath);
        
        // (A) Le répertoires, existe et on peut le lire
        checkInputFolder(rootFolder);
        logger.info("Repertoire '" + folderPath + "' trouvé");
        
        // (B) Verification des genres
        ComparaisonFsDb syncLevel1 = checkGenres(rootFolder);
        if (!syncLevel1.isValid()) {
            logger.error("Genres invalides" + syncLevel1.toString());
            System.exit(-1);
        }
        logger.info("Les genres sont synchronisés");
        
        // (C) Verification des sous-genres
        stream(rootFolder.listFiles(File::isDirectory)). //
            forEach(lvl1 -> {
              ComparaisonFsDb syncLevel2 = checkSubGenres(lvl1);
              if (!syncLevel2.isValid()) {
                logger.error("SousGenres invalides dans '" + lvl1 + "' " + syncLevel2.toString());
                System.exit(-2);
              }
            }
        );
        logger.info("Les sous-genres sont synchronisés");
        
        // (D) Analyse des documentaires genre par genre
        stream(rootFolder.listFiles(File::isDirectory)).//
                forEach(file -> analyseRepertoire(file));
    }
    
    /**
     * First control, the folder must exist.
     *
     * @param folderPath
     *      input folder
     * @throws FileNotFoundException
     *      repertoire non trouvé
     */
    public void checkInputFolder(File rootFolder) throws FileNotFoundException {
       if (!rootFolder.exists() || !rootFolder.isDirectory() || !rootFolder.canRead()) {
            logger.error("Impossible de trouver '" + rootFolder.getPath() + "' vérifier le chemin SVP");
            throw new FileNotFoundException("Le repertoire " + rootFolder.getPath() + " n'existe pas ou n'est pas lisible, vérifier le chemin SVP");
        }
    }
  
    /**
     * List file in DB, find not existing in FS and create related.
     *
     * @param folderPath
     *      current folderNaùe
     * @param createMissing
     *      create directory if not present
     * @return
     *      anomalie number detected
     */
    public ComparaisonFsDb checkGenres(File rootFolder) {
        return compareFSandDb(stream(rootFolder.listFiles(File::isDirectory)).
                map(File::getName).collect(Collectors.toCollection(TreeSet::new)), 
                refDbDao.getSetOfTopLevelGenreName());
    }
   
    /**
     * Check subfolder '--'
     * 
     * @param dir
     *      repertoire
     * @param parentGenre
     *      identifiant du parent dans la base
     * @return
     *      si le system est valid
     */
    public ComparaisonFsDb checkSubGenres(File dir) {
        return compareFSandDb(stream(
                // List folders starts by "--"
                dir.listFiles(file->file.isDirectory() && file.getName().startsWith("--"))).
                // remove the dash at start
                map(file->file.getName().replaceAll("-- ", "")).//
                // collect as a Set
                collect(Collectors.toCollection(TreeSet::new)),
                // Set in BDD with : directoryName -> genreId -> Childs
                new HashSet<>(refDbDao.getListOfChildGenre(refDbDao.searchGenreIdByGenreName(dir.getName()))));
    }
    
    /**
     * Analyse d'un répertoire.
     *
     * @param rootFolder
     *      current folder
     */
    public void analyseRepertoire(File rootFolder) {        
       
        GenreDto currentGenre = getGenreByFolderName(rootFolder);
        logger.info(rootFolder.getName() + " (genre=" + currentGenre.getId() + ")");
        
        // Gestion des séries comme un lot 
        if (ff4j.check("handleSerie")) {
            Set < String > seriesDB = serieDbDao.getSerieNamesByGenre(currentGenre.getId());
            Map < String, SmartSerieName > seriesFS = listeSeries(rootFolder);     
            ComparaisonFsDb fsdb1 = compareFSandDb(seriesFS.keySet(), seriesDB);
            if (!fsdb1.getNotOnFileSystem().isEmpty()) {
                logger.error("Erreur: Documentaires uniquement en BDD " + fsdb1.getNotOnFileSystem());
                System.exit(-9);
            }
            // On crée les series qui ne sont pas encore en base
            fsdb1.getNotInDatabase().stream().forEach(s-> createSerie(seriesFS.get(s)));
            // On analyse toutes les séries (création et update des episodes)
            seriesFS.values().stream().forEach(ssn -> analyseSerie(ssn));
        }
        
        // Gestion des documentaires comme un lot
        if (ff4j.check("handleDocus")) {
            Map < String, Integer >       docusDB = docuDbDao.getDocumentaireNamesMapByGenre(currentGenre.getId());
            Map < String, SmartFileName > docusFS = listeDocumentaires(rootFolder);
            ComparaisonFsDb fsdb = compareFSandDb(docusFS.keySet(), docusDB.keySet());
            if (!fsdb.getNotOnFileSystem().isEmpty()) {
                logger.error("Documentaires en base et pas sur le disque (FATAL):");
                for(String titre : fsdb.getNotOnFileSystem()) {
                    logger.error("id=" + docusDB.get(titre) + ", titre=" + titre);
                }
                System.exit(-10);
            }
            // On analyse tous les documentaires (certains update)
            docusFS.values().stream().forEach(sfm -> analyseDocumentaire(sfm, currentGenre.getId()));
        }
        
        // Appels Récursif si commence par "--" (sous-genre)
        stream(rootFolder.listFiles()).filter(sousGenreFolder()).forEach(file -> analyseRepertoire(file));
    }
    
    /**
     * Liste des documentaires dans le répertoire
     */
    private Map < String, SmartFileName > listeDocumentaires(File parent) {
        return stream(parent.listFiles()).
                filter(validFolderDocumentaires()).//
                map(file -> new SmartFileName(file)).//
                collect(Collectors.toMap(SmartFileName::getTitre, Function.identity()));
    }
    
    /**
     * Get genre id from Name.
     *
     * @param folder
     *      target folder
     * @return
     */
    private GenreDto getGenreByFolderName(File folder) {
        return refDbDao.getGenreById(
                refDbDao.searchGenreIdByGenreName(
                        folder.getName().replaceAll("-- ", "")));
    }
    
    /**
     * Liste tous les dossiers qui sont des séries. [SERIE]
     * 
     * @param parent
     *      liste des séries
     * @return
     *      liste des sériess
     */
    private Map < String, SmartSerieName > listeSeries(File parent) {
        return stream(parent.//
                listFiles(f->f.isDirectory() && f.getName().startsWith("[SERIE] - "))).//
                map(file -> new SmartSerieName(file)).//
                collect(Collectors.toMap(SmartSerieName::getTitre, Function.identity()));
    }
    
    /**
     * Compare 2 sets a detect discrepancies.
     *
     * @param fsSet
     *      set on file system
     * @param dbSet
     *      set on DB
     * @return
     */
    private ComparaisonFsDb compareFSandDb(Set < String > fsSet, Set < String > dbSet) {
        ComparaisonFsDb result = new ComparaisonFsDb();
        result.getNotInDatabase().addAll(fsSet);
        result.getNotInDatabase().removeAll(dbSet);
        result.getNotOnFileSystem().addAll(dbSet);
        result.getNotOnFileSystem().removeAll(fsSet);
        return result;
    }
    
    /**
     * Permet de créer les series si nécessaires et détecte les séries en BDD et pas sur FS
     *
     * @param rootFolder
     *      repertoire de travail
     */
    private void createSerie(SmartSerieName ssn) {
      if (serieDbDao.exist(ssn.getTitre())) {
          throw new IllegalArgumentException("Une serie existe deja avec le titre " + ssn.getTitre());
      }
      if (!modeSimulation) {
          serieDbDao.createSerie(ssn.getTitre(), 
                  getGenreByFolderName(ssn.getFolder().getParentFile()).getId(), 
                  ssn.getAnneeStart(),ssn.getAnneeEnd(), ssn.isVo());
      }
      logger.info("Création de : '" + ssn.getTitre() + "' (" + ssn.getFolder().getName() + ")");
    }
    
    /**
     * Nous sommes sur un répertoire de série.
     *
     * @param folder
     */
    private void analyseSerie(SmartSerieName ssn) {        
        logger.info("Serie [" + ssn.getTitre() + "]");
        SerieDto dto = serieDbDao.getSerieById(serieDbDao.getSerieIdByName(ssn.getTitre()));
        
        // Recherche le pattern "NN - TITRE",x dossier ou fichier
        File[] episodes = ssn.getFolder().listFiles(file -> new SmartFileName(file.getName()).startByNumber());
       
        // Ce ne sont PAS des épisodes, alors sans doute des Saisons 
        if (episodes == null || episodes.length == 0) {
            // Liste des saisons du coup
            File[] saisons = ssn.getFolder().listFiles(file -> file.getName().startsWith("Saison"));
            // Ni des episodes, ni des saisons ==> Erreur ICI
            if (saisons == null || saisons.length == 0) {
               logger.error("ERREUR dans le répertoire " + ssn.getFolder().getAbsolutePath() + " no Saison et pas de 01 XXX");
                System.exit(-5);
            }
            // Boucle sur les saisons
            for (File saisonX : saisons) {
                int annee = 1900;
                int nbSaison = 0;
                
                // Recherche du numéro de saison X et de la date avec le pattern Saison X (date)
                String tmps = saisonX.getName().replaceAll("Saison", "");
                if (tmps.lastIndexOf("(") != -1) {
                    // Le format est Saison AA (YYYY)
                    nbSaison = Integer.parseInt(tmps.substring(0, tmps.lastIndexOf("(")).trim());
                    annee = Integer.parseInt(tmps.substring(tmps.lastIndexOf("(") + 1, tmps.length() - 1));
                    logger.debug("Titre=" + dto.getTitre() + 
                            "(" + dto.getId() + ") Saison=" + nbSaison + " annee=" + annee);
                } else {
                    // Le format est Saison AA, pas de date
                    nbSaison = Integer.parseInt(tmps.trim());
                    // Logger pour traitements
                    logger.warn("Cannot find annee of saison " + saisonX.getAbsolutePath());
                }

                // On donne un répertoire de Saison contenant forcément des épisodes
                analyseSaison(saisonX, dto.getId(), nbSaison, annee);
            }
         
        } else {
            // L'annee est portée par le répertoire parent (ou chaque episode)
            String annee = ssn.getFolder().getName().substring(
                    ssn.getFolder().getName().lastIndexOf("(")+1, ssn.getFolder().getName().length() - 1);
          
            // Si pas d'annee trouve on met 1900 comme valeur par defaut
            // Dans le répertoire de la serie on a trouve des épisodes (pas de saison)
            analyseSaison(ssn.getFolder(), 
                   dto.getId(), 1, isNumeric(annee) ? Integer.parseInt(annee) : 1900);
        }
    }
    
    /**
     * Format execution time for log.
     *
     * @param executionTime
     *          current execution time in millis
     * @return
     */
    public String formatExecutionTime(long executionTime) {
        long min = executionTime / (1000 * 60L);
        executionTime = executionTime - min * 1000 * 60L;
        long sec = executionTime / 1000L;
        long millis = executionTime - sec * 1000L;
        StringBuilder sb = new StringBuilder();
        sb.append(min + " min. ");
        sb.append(sec + " sec. ");
        sb.append(millis + " millis");
        return sb.toString();
    }
    
    /**
     * Analayse d'une SAISON avec des episodes attendus
     * @param folderSaison
     *      le répertoire de la saison (peut etre la serie si unique)
     * @param serie
     *      l'identifiant de la série
     * @param nbSaison
     *      numero de la saison
     * @param annee
     *      annee pour cette saison
     */
    private void analyseSaison(File folderSaison, int serie, int nbSaison, int annee) {
        SerieDto dto = serieDbDao.getSerieById(serie);
        
        // Recherche le pattern "NN - EPISODE NAME"
        File[] episodes = folderSaison.listFiles(file -> new SmartFileName(file.getName()).startByNumber());
        
        // On s'attend forcément a avoir des episodes ici alors erreur au besoin
        if (episodes == null || episodes.length == 0) {
            logger.error("ERREUR dans le répertoire " + folderSaison.getAbsolutePath() + " Pas episodes");
            System.exit(-4);
        }
        
        // On vérifie si l'on travaille avec des répertoires
        if (!episodes[0].isDirectory()) {
            
            // On liste les fichiers qui ne sont pas des sous titres
            for (File episode : folderSaison.listFiles(f -> f.isFile() && 
                    !f.getName().endsWith("srt") && 
                    !f.getName().endsWith("txt"))) {
                    
              // Lecture du fichier VIDEO pour initialiser un episode
              Episode ep = new Episode(new MovieMetadata(episode.getAbsolutePath()));
                    
              // Information complémentaires sur le fichier
              SmartFileName smf = new SmartFileName(episode.getName());
              ep.setEpisode(smf.getNumber());
              ep.setTitre(smf.getTitre());
              ep.setVo(smf.isVo());
              ep.setExtension(smf.getExtension());
              ep.setAnnee(smf.containsAnnee() ? smf.getAnnee() : annee);
              ep.setSaison(nbSaison);
              ep.setSerie(serie);
              analyseEpisode(ep, dto);
            }
        } else {
           
            // liste des répertoires respectant le format "NN - Titre"
            for (File episode : folderSaison.listFiles(f -> f.isDirectory() && 
                    new SmartFileName(f.getName()).startByNumber())) {
                
                SmartFileName smfr =  new SmartFileName(episode.getName());
                
                // Initialisation de l'enregistrement
                Episode ep = new Episode();
                ep.setEpisode(smfr.getNumber());
                ep.setTitre(smfr.getTitre());
                ep.setVo(smfr.isVo());
                ep.setAnnee(smfr.containsAnnee() ? smfr.getAnnee() : annee);
                ep.setSaison(nbSaison);
                ep.setSerie(serie);
                stream(episode.listFiles()).filter(validMovieFiles()).
                        forEach(f -> ep.updateMovieMetaData(new MovieMetadata(f.getAbsolutePath())));
                analyseEpisode(ep, dto);
            }
        }
    }
    
    private void analyseEpisode(Episode ep, SerieDto serie) {
        logger.info(ep.getSaison() + "x" + ep.getEpisode() + " - " + ep.getTitre());
        // Episode existe Déjà
        if (episodeDbDao.existID(ep.getSerie(), ep.getSaison(), ep.getEpisode())) {
            // Comparaison des titres
            Episode old = episodeDbDao.getEpisodeById(ep.getSerie(), ep.getSaison(), ep.getEpisode());
            if (!old.getTitre().equalsIgnoreCase(ep.getTitre())) {
                logger.error("Titre episode désynchronisés ");
                logger.error("serie=" + serie.getTitre());
                logger.error("db=" + old.getTitre());
                logger.error("fs=" + ep.getTitre());
                logger.error("SELECT * FROM t_episode WHERE (SERIE = " + ep.getSerie() + ") and "
                        + "(SAISON = "+ep.getSaison()+") AND "
                        + "(EPISODE = "+ep.getEpisode()+")");
                System.exit(-7);
            // Les épisodes ont le même nom
            } else if (old.getFormat() == null || old.getFormat().isEmpty()) {
                logger.info("-> update :" + ep.toString());;
                if (!modeSimulation) {
                    episodeDbDao.updateEpisodeFileInformation(ep);
                }
            }
        } else {
            logger.info("-> Create " + ep);
            if (!modeSimulation) {
                episodeDbDao.createEpisode(ep, serie.getImage());
            }
        }
    }
    
    /**
     * Analyse de documentaire (simple).
    *
    * @param repertoireDocu
    *      nom du documentaire
    * @param genre
    *      genre associe (depuis le répertoire parent)
    */
   public void analyseDocumentaire(SmartFileName smf, int idGenre) {
       File repertoireDocu = smf.getCurrentFolder();
       logger.info(repertoireDocu.getName());
       Documentaire docu = new Documentaire(smf);
       docu.setGenre(idGenre);
       stream(repertoireDocu.listFiles()).filter(validMovieFiles()).
           forEach(f -> docu.updateMovieMetaData(new MovieMetadata(f.getAbsolutePath())));
       if (!docuDbDao.exist(docu.getTitre(), idGenre)) {
           logger.info("-> Create " + docu);
           if (!modeSimulation) {
               docuDbDao.create(docu);
           }
       } else if (docu.getFormat() == null || "".equals(docu.getFormat())) {
           logger.info("-> Update " + docu);
           if (!modeSimulation) {
               docuDbDao.updateMetaData(idGenre, docu);
           }
       }
   }
  
    
    /**
     * Simple way to check if a value is a integer.
     *
     * @param str
     *      current string
     * @return
     */
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    /**
     * Getter accessor for attribute 'modeSimulation'.
     *
     * @return
     *       current value of 'modeSimulation'
     */
    public boolean isModeSimulation() {
        return modeSimulation;
    }

    /**
     * Setter accessor for attribute 'modeSimulation'.
     * @param modeSimulation
     * 		new value for 'modeSimulation '
     */
    public void setModeSimulation(boolean modeSimulation) {
        this.modeSimulation = modeSimulation;
    }
}
