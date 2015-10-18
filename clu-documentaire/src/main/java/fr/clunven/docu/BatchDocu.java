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
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext-docus.xml");
        BatchDocu batch = new BatchDocu();
        batch.setDocuServices((DocuServices) appCtx.getBean("docu.service"));
        batch.processFolder("E:/Documentaires");
        appCtx.close();
    }

    
    public void processFolder(String folderPath) {
       
        long start = System.currentTimeMillis();
        
        
        // 00 - Validate incoming folder
        File rootFolder = new File(folderPath);
        if (!rootFolder.exists() || !rootFolder.isDirectory() || !rootFolder.canRead()) {
            logger.error("Le repertoire " + folderPath + " n'existe pas ou n'est pas lisible, vérifier le chemin SVP");
            throw new IllegalArgumentException("Le repertoire " + folderPath + " n'existe pas ou n'est pas lisible, vérifier le chemin SVP");
        }
        
        // 01 - Validate all genres (1st level)
        SyncFolderResult lvl1 = docuServices.syncFileSystemAndDB(rootFolder, true);
        if (!lvl1.isValid()) {
            logger.error("FS and DB are not synchronized, exit");
            System.exit(-1);
        }
        logger.info("Niveau 1 est valide");
        
        // 02 - Synchronize all subfolders
        Set < File> folders = Arrays.stream(rootFolder.
                listFiles(File::isDirectory)).//
                collect(Collectors.toCollection(TreeSet::new));
        
        folders.stream().forEach(file -> docuServices.syncFileSystemAndDBSub(file, docuServices.getDocudb().getGenreId(file.getName())));
        logger.info("Niveau 2 est valide");
        
        // 03 - Analyse des documentaires
        folders.stream().forEach(file -> docuServices.analyseRepertoire(file)); 
        
        long end = System.currentTimeMillis();
        logger.info("Traitement Terminé !! en " + (end-start) + " millis.");
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
