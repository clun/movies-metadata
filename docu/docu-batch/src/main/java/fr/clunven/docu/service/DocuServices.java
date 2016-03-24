package fr.clunven.docu.service;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.clunven.docu.dao.db.DocumentaryDbDao;
import fr.clunven.docu.dao.db.EpisodeDbDao;
import fr.clunven.docu.dao.db.SerieDbDao;
import fr.clunven.docu.domain.SyncFolderResult;

/**
 * Documentaires services.
 * 
 * @author Cedrick Lunven (@clunven)</a>
 */
@Service("docu.service.batch")
public class DocuServices {

    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(DocuServices.class);
   
    @Autowired
    private DocumentaryDbDao docuDbDao;
    
    @Autowired
    private EpisodeDbDao episodeDbDao;
    
    @Autowired
    private SerieDbDao serieDbDao;
    
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
    public SyncFolderResult syncFileSystemAndDB(File rootFolder , boolean createMissing) {
        SyncFolderResult result = new SyncFolderResult();
        
        /* List filesystem directories
        Set< String > setOfFolderNames = Arrays.stream(rootFolder.
                listFiles(File::isDirectory)).//
                map(File::getName).//
                collect(Collectors.toCollection(TreeSet::new));
        
        for(String genre : docudb.getListOfGenres()) {
            //
            if (!setOfFolderNames.contains(genre)) {
                logger.warn("'" + rootFolder.getAbsolutePath() + File.separator + genre + "' not found on fileSystem");
                result.getNotOnFileSystem().add(rootFolder.getAbsolutePath() + File.separator + genre);
                if (createMissing) {
                   logger.info(" + Creation repertoire : " + genre);
                   new File(rootFolder.getAbsolutePath() + File.separator + genre).mkdirs();
                }
            }
        }
        
        for(String titre : setOfFolderNames) {
            //logger.info(" + FOLDER : " + titre);
            if (!docudb.getListOfGenres().contains(titre)) {
                result.getNotInDatabase().add(titre);
                logger.warn("'" + titre + "' non trouvé dans la base de donnée");
            } else {
                logger.info("[OK] " + titre);
            }
        }*/
        
        return result;
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
    public SyncFolderResult syncFileSystemAndDBSub(File dir) {
        SyncFolderResult resultat = new SyncFolderResult();
      
        Set< String > setOfFolderNames = Arrays.stream(
                // List folders starts by "--"
                dir.listFiles(file->file.isDirectory() && file.getName().startsWith("--"))).
                // remove the dash at start
                map(file->file.getName().replaceAll("-- ", "")).//
                // collect as set
                collect(Collectors.toCollection(TreeSet::new));
                
        /*logger.info("FS=>DB : Dossiers:" + dir.getAbsolutePath() + 
        //        " avec " + setOfFolderNames.size() + " repertoires: " + setOfFolderNames);
       
        // Recherche du genre depuis le nom du répertoire (eg: Astronomie)
        int genreAssocie = docudb.searchGenreIdByGenreName(dir.getName());
        //logger.info("FS=>DB : Un genre associe a ete trouvé dans la base :" + genreAssocie);
        
        // Recherche des genres "enfants" (-- BigBang)
        logger.info(dir.getName());
        List < String > children = docudb.getListOfChildGenre(genreAssocie);
        for(String subfolder : setOfFolderNames) {
            if (!children.contains(subfolder)) {
                resultat.getNotInDatabase().add(subfolder);
            } else {
                logger.info("[FS->DB][OK] " + subfolder);
            }
        }
        
        // Get list of children categorie
        //logger.info("DB=>FS : Genre:" + genreAssocie + " avec " + children.size() + " enfants: " + children);
        for(String child : children) {
            if (!setOfFolderNames.contains(child)) {
                logger.warn("Expected '" + child + "' not found on fileSystem within " + dir.getName());
                new File(dir.getAbsolutePath() + File.separator + "-- " + child).mkdirs();
                resultat.getNotOnFileSystem().add(dir.getAbsolutePath() + File.separator + "-- " + child);
            } else {
                logger.info("[DB->FS][OK] " + child);
            }
        }
        
        //logger.info(dir.getAbsolutePath() + ":" + resultat.isValid());
        if (!resultat.isValid()) {
            logger.error("Répertoire " + dir.getAbsolutePath() + " invalide : \r\n" + resultat.toString());
            System.exit(-2);
        }*/
        return resultat;
    }
    
    /**
     * Permet de créer les series.
     *
     * @param rootFolder
     *     
     */
    public void createSeries(File rootFolder) {
        
        /* Liste des SERIES
        Set< File > series = Arrays.stream(
                rootFolder.listFiles(file->file.isDirectory() && file.getName().startsWith("[SERIE] - "))).
                collect(Collectors.toCollection(TreeSet::new));
        
        for (File serie : series) {
           SmartSerieName sfmSerie = new SmartSerieName(serie.getName());
           if (!docudb.isSerieExist(sfmSerie.getTitre())) {
               logger.info("Creation de la serie : '" + sfmSerie.getTitre() + "' (" + serie.getAbsolutePath() + ")");
               int genre = docudb.searchGenreIdByGenreName(rootFolder.getName().replaceAll("-- ", ""));
               docudb.createSerie(sfmSerie.getTitre(), genre, sfmSerie.getAnneeStart(), sfmSerie.getAnneeEnd(), sfmSerie.isVo());
           }
        }*/
    }
    
    /**
     * Appel en récursif pour analyser un répertoire.
     *
     * @param rootFolder
     */
    public void analyseRepertoire(File rootFolder) {
        
        // Ajouter les séries éventuellement manquantes dans la base de données.
        createSeries(rootFolder);
        
        // Gestion des séries au niveau 1 : Documentaires > Genre > [SERIE]
        Arrays.stream(rootFolder.listFiles(file->file.isDirectory() && file.getName().startsWith("[SERIE] - "))).
                collect(Collectors.toCollection(TreeSet::new)).forEach(file -> analyseSerie(file));
        
        // Gestion des séries au niveau 2..n (par recursivité)
        Arrays.stream(rootFolder.listFiles(file->file.isDirectory())).
                collect(Collectors.toCollection(TreeSet::new)).forEach(file -> analyseRepertoire(file));
        
        // Analysons maintenant les répertoires qui ne sont pas des series ==> Les documentaires
        Arrays.stream(rootFolder.listFiles(
                file->file.isDirectory() && 
                !file.getName().startsWith("[SERIE] - ") &&
                !file.getName().startsWith("--") &&
                !file.getName().startsWith("Saison") &&
                !Character.isDigit(file.getName().charAt(0)))).
            collect(Collectors.toCollection(TreeSet::new)).forEach(file -> processDocumentaire(file));
    }
    
    /**
     * Nous sommes sur un répertoire de série.
     *
     * @param folder
     */
    private void analyseSerie(File folder) {
        logger.debug("Processing Serie: " + folder.getName());
        
        /*Pour le nom de la serie on enleve le prefixe et les eventuelles dates
        String serieName = folder.getName().replaceAll("\\[SERIE\\] - ", "").replaceAll("\\(vo\\)", "").trim();
        if (serieName.lastIndexOf("(") != -1) {
            serieName = serieName.substring(0,  serieName.lastIndexOf("(")).trim();
        }
        
        / Collecte de l'identifiant depuis le nom
        int serieID = docudb.getSerieIdBySerieName(serieName);
        
        // Recherche le pattern NN - Element
        File[] episodes = folder.listFiles(file -> new SmartFileName(file.getName()).startByNumber());
        
        // Ce ne sont PAS des épisodes, alors sans doute des Saisons 
        if (episodes == null || episodes.length == 0) {
            
            // Liste des saisons du coup
            File[] saisons = folder.listFiles(file -> file.getName().startsWith("Saison"));
            
            // Ni des episodes, ni des saisons ==> Erreur ICI
            if (saisons == null || saisons.length == 0) {
               logger.error("ERREUR dans le répertoire " + folder.getAbsolutePath() + " no Saison et pas de 01 XXX");
                System.exit(-3);
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
                    logger.debug("Titre=" + serieName + "(" + serieID + ") Saison=" + nbSaison + " annee=" + annee);
                } else {
                    // Le format est Saison AA, pas de date
                    nbSaison = Integer.parseInt(tmps);
                    // Logger pour traitements
                    logger.warn("Cannot find annee of saison " + saisonX.getAbsolutePath());
                }

                // On donne un répertoire de Saison contenant forcément des épisodes
                processSaison(saisonX, serieID, nbSaison, annee);
            }
         
        } else { // Dans le répertoire de la serie on a trouve des épisodes (pas de saison)
            
            // Alors on en conclue une saison unique
            int nbSaison = 1 ;
            
            // L'annee est portée par le répertoire parent (ou chaque episode)
            String annee = folder.getName().substring(folder.getName().lastIndexOf("(")+1, folder.getName().length() - 1);
          
            // Si pas d'annee trouve on met 1900 comme valeur par defaut
            processSaison(folder, serieID, nbSaison, isNumeric(annee) ? Integer.parseInt(annee) : 1900);
        }*/
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
    private void processSaison(File folderSaison, int serie, int nbSaison, int annee) {
        
        /* Recherche le pattern NN - Element
        File[] episodes = folderSaison.listFiles(file -> new SmartFileName(file.getName()).startByNumber());
        
        // On s'attend forcément a avoir des episodes ici alors erreur au besoin
        if (episodes == null || episodes.length == 0) {
            logger.error("ERREUR dans le répertoire " + folderSaison.getAbsolutePath() + " Pas episodes");
            System.exit(-4);
        }
        
        // On vérifie si l'on travaille avec des répertoires ou non
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
              
              // Creation de l'episode dans la base (si non existant), ou update
              docudb.createEpisode(ep);
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
                
                // Creation si non existant, update sinon
                docudb.createEpisode(ep);
            }
        }*/
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
