package fr.clunven.docu;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.clunven.docu.domain.SyncFolderResult;
import fr.clunven.docu.service.DocuServices;

/**
 * Main class for this BATCH.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class BatchDocu {

    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(BatchDocu.class);
  
    /** injecting services. */
    private DocuServices docuServices;
   
    /**
     * Main treatment.
     * @param args
     *      no arguments
     */
    public static void main(String[] args) {
        /*
         * Initialisation du contexte Spring.
         */
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext-docus.xml");
        BatchDocu batch = new BatchDocu();
        batch.setDocuServices((DocuServices) appCtx.getBean("docu.service"));
        
        long start = System.currentTimeMillis();
        String folderPath = "D:/Documentaires";
        
        /*
         * A) Le répertoires, existe et on peut le lire
         */
        batch.validateInputFolder(folderPath);
        
        /* 
         * B) Verification des genres
         * 
         * - Liste des genres en bases de données, on créer les répertoires manquant
         * - Liste des genres sur disque, si non en base alerte
         */
        batch.validateLevel1(folderPath);
        
        /*
         *  C) Verification des sous-genres
         *  
         * - On part des sous répertoires (Genre principaux)
         * - Pour chacun on lance le traitement son nom et identifiant BDD associé
         *    1) On vérifie que le genre existe depuis le nom du répertoire
         *    2) On cherche les enfants en base depuis l'id du genre
         *    3) On boucle sur les sous répertoires (--) pour verifier que ca existe en base
         *    4) On boucle sur les sous genre en base pour vérifier que tous les répertoires sont crées
         */
        Set < File> sousRepertoires = Arrays.stream(new File(folderPath).
                listFiles(File::isDirectory)).//
                collect(Collectors.toCollection(TreeSet::new));
        sousRepertoires.stream().forEach(file -> batch.docuServices.syncFileSystemAndDBSub(file));
        batch.logger.info("Niveau 2 est valide");
        
        //  D) Analyse des documentaires genre par genre
        // sousRepertoires.stream().forEach(file -> batch.docuServices.analyseRepertoire(file)); 
        
        long end = System.currentTimeMillis();
        //batch.logger.info("Traitement Terminé !! en " + (end-start) + " millis.");
        
        appCtx.close();
    }

    private void validateInputFolder(String folderPath) {
        File rootFolder = new File(folderPath);
        if (!rootFolder.exists() || !rootFolder.isDirectory() || !rootFolder.canRead()) {
            logger.error("Le repertoire " + folderPath + " n'existe pas ou n'est pas lisible, vérifier le chemin SVP");
            throw new IllegalArgumentException("Le repertoire " + folderPath + " n'existe pas ou n'est pas lisible, vérifier le chemin SVP");
        }
        logger.info("Le repertoire existe");
    }
    
    private void validateLevel1(String folderPath) {
        SyncFolderResult lvl1 = docuServices.syncFileSystemAndDB(new File(folderPath), true);
        if (!lvl1.isValid()) {
            logger.error("FS and DB are not synchronized, exit");
            System.exit(-1);
        }
        logger.info("Niveau 1 est valide");
    }

    /**
     * Getter accessor for attribute 'docuServices'.
     *
     * @return
     *       current value of 'docuServices'
     */
    public DocuServices getDocuServices() {
        return docuServices;
    }

    /**
     * Setter accessor for attribute 'docuServices'.
     * @param docuServices
     * 		new value for 'docuServices '
     */
    public void setDocuServices(DocuServices docuServices) {
        this.docuServices = docuServices;
    }
}
