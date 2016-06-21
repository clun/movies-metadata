package fr.clunven.docu.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

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
import fr.clunven.docu.domain.Episode;
import fr.clunven.docu.domain.SmartFileName;
import fr.clunven.docu.domain.SmartSerieName;
import fr.clunven.docu.media.General;
import fr.clunven.docu.media.MovieMetadata;
import fr.clunven.docu.media.Video;

/**
 * Documentaires services.
 * 
 * @author Cedrick Lunven (@clunven)</a>
 */
@Service("docu.batch.service")
public class DocuServices {

    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(DocuServices.class);
    
    @Autowired
    private ReferentialDbDao refDbDao;
    
    @Autowired
    private DocumentaryDbDao docuDbDao;
    
    @Autowired
    private EpisodeDbDao episodeDbDao;
    
    @Autowired
    private SerieDbDao serieDbDao;
    
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
        return compareFSandDb(
                Arrays.stream(rootFolder.listFiles(File::isDirectory)).map(File::getName).
                collect(Collectors.toCollection(TreeSet::new)), 
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
        return compareFSandDb(Arrays.stream(
                // List folders starts by "--"
                dir.listFiles(file->file.isDirectory() && file.getName().startsWith("--"))).
                // remove the dash at start
                map(file->file.getName().replaceAll("-- ", "")).//
                // collect as set
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
        Map < String, SmartSerieName > seriesFS = listeSeries(rootFolder);        
        createSeries(seriesFS, currentGenre.getId());
        
        // Analyse des episodes (creation, erreur)
        seriesFS.values().stream().forEach(ssn -> analyseSerie(ssn));
        
        // Appels Récursif si commence par "--" (sous-genre)
        Arrays.stream(rootFolder.listFiles(file->file.isDirectory() && file.getName().startsWith("--"))).
                collect(Collectors.toCollection(TreeSet::new)).forEach(file -> analyseRepertoire(file));
        
        /* Analysons maintenant les répertoires qui ne sont pas des series ==> Les documentaires
        Arrays.stream(rootFolder.listFiles(
                file->file.isDirectory() && 
                !file.getName().startsWith("[SERIE] - ") &&
                !file.getName().startsWith("--") &&
                !file.getName().startsWith("Saison") &&
                !Character.isDigit(file.getName().charAt(0)))).
            collect(Collectors.toCollection(TreeSet::new)).forEach(file -> processDocumentaire(file));
            */
    }
    
    /** Synchronization Series. */
    private void createSeries(Map < String, SmartSerieName > seriesFS, int genre) {
        // Liste des séries en base
        Set < String > setOfSeriesInDb = serieDbDao.getSeriesByGenre(genre).stream(). //
                map(s -> s.getTitre()). //
                collect(Collectors.toCollection(TreeSet::new));        
                
        // Existence et Genres sont les mêmes
        ComparaisonFsDb compare = compareFSandDb(seriesFS.keySet(), setOfSeriesInDb);
        if (!compare.getNotOnFileSystem().isEmpty()) {
            logger.error("Series non trouvées sur le disque " + compare.toString());
            System.exit(-4);
        }
        
        // Chaque série non trouvée est créée
        compare.getNotInDatabase().stream().forEach(s-> createSerie(seriesFS.get(s)));
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
      serieDbDao.createSerie(ssn.getTitre(), 
              getGenreByFolderName(ssn.getFolder().getParentFile()).getId(), 
              ssn.getAnneeStart(),ssn.getAnneeEnd(), ssn.isVo());
      logger.info("Création de : '" + ssn.getTitre() + "' (" + ssn.getFolder().getName() + ")");
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
        return Arrays.stream(parent.//
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
                    nbSaison = Integer.parseInt(tmps);
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
     * Analyse de documentaire (simple).
     *
     * @param repertoireDocu
     *      nom du documentaire
     * @param genre
     *      genre associe (depuis le répertoire parent)
     */
    private void processDocumentaire(File repertoireDocu) {
        logger.debug("Processing Documentaire " + repertoireDocu.getName());
        
        /* Lecture du fichier VIDEO pour initialiser un episode
        Documentaire docu = new Documentaire();
              
        // Information complémentaires sur le fichier
        SmartFileName smf = new SmartFileName(repertoireDocu.getName());
        docu.setTitre(smf.getTitre());
        docu.setVo(smf.isVo());
        docu.setAnnee(smf.containsAnnee() ? smf.getAnnee() : 1900);
        
        //genre ?
        long duree = 0;
        long fileSize = 0;
        for (File chunk : repertoireDocu.listFiles(File::isFile)) {
            // Pour le format on prend les éléments du premiers
            MovieMetadata mmd = new MovieMetadata(chunk.getAbsolutePath());
            if (null == docu.getFormat() && mmd.get(General.FORMAT).isPresent()) {
                docu.setFormat(mmd.get(General.FORMAT).get());
            }
            if (0 == docu.getBitrate() && mmd.get(Video.BITRATE).isPresent()) {
                docu.setBitrate(Long.parseLong(mmd.get(Video.BITRATE).get()));
            }
            if (null == docu.getResolution() && mmd.get(Video.WIDTH).isPresent() && mmd.get(Video.HEIGHT).isPresent()) {
                docu.setResolution(mmd.get(Video.WIDTH).get() + "x" + mmd.get(Video.HEIGHT).get());
            }
            
            // FileSize et durée seront obtenus en additionnant les morceaux
            
            if (mmd.get(General.DURATION).isPresent()) {
                duree += Double.parseDouble(mmd.get(General.DURATION).get());
            }
            if (mmd.get(General.FILESIZE).isPresent()) {
                fileSize += Long.parseLong(mmd.get(General.FILESIZE).get());
            }
        }
        docu.setDuree(duree);
        docu.setTaille(fileSize);
        try {
            docu.setGenre(docudb.searchGenreIdByGenreName(repertoireDocu.getParentFile().getName().replaceAll("-- ", "")));
            docudb.createDocumentaire(docu);
        } catch(IllegalArgumentException e) {
            logger.error(" +++ ERROR DOCUMENTAIRE " + repertoireDocu.getName(), e);
        }*/
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
                    !f.getName().endsWith("srt") && !f.getName().endsWith("txt") 
                    )) {
                    
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
                
                // Pour rechercher les elements
                long fileSize = 0;
                long duree = 0;
                for (File chunk : episode.listFiles(File::isFile)) {
                    // Pour le format on prend les éléments du premiers
                    MovieMetadata mmd = new MovieMetadata(chunk.getAbsolutePath());
                    if (null == ep.getFormat() && mmd.get(General.FORMAT).isPresent()) {
                        ep.setFormat(mmd.get(General.FORMAT).get());
                    }
                    if (0 == ep.getBitrate() && mmd.get(Video.BITRATE).isPresent()) {
                        ep.setBitrate(Long.parseLong(mmd.get(Video.BITRATE).get()));
                    }
                    if (null == ep.getResolution() && mmd.get(Video.WIDTH).isPresent() && mmd.get(Video.HEIGHT).isPresent()) {
                        ep.setResolution(mmd.get(Video.WIDTH).get() + "x" + mmd.get(Video.HEIGHT).get());
                    }
                    
                    // FileSize et durée seront obtenus en additionnant les morceaux
                    
                    if (mmd.get(General.DURATION).isPresent()) {
                        duree += Long.parseLong(mmd.get(General.DURATION).get());
                    }
                    if (mmd.get(General.FILESIZE).isPresent()) {
                        fileSize += Long.parseLong(mmd.get(General.FILESIZE).get());
                    }
                }
                ep.setDuree(duree);
                ep.setTaille(fileSize);
                analyseEpisode(ep, dto);
            }
        }
    }
    
    private void analyseEpisode(Episode ep, SerieDto serie) {
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
            } else {
                logger.info(ep.getSaison() + "x" + ep.getEpisode() + " - " + ep.getTitre());
            }
        } else {
            logger.info("Create [" + serie.getTitre() + "] - " + ep.getSaison() + "x" + ep.getEpisode() +  " - " + ep.getTitre());
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
}
