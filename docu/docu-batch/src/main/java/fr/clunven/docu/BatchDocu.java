package fr.clunven.docu;

import java.io.File;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.clunven.docu.domain.ComparaisonFsDb;
import fr.clunven.docu.service.DocuServices;

/**
 * Main class for this BATCH.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class BatchDocu {

    /** logger for this class. */
    private static Logger logger = LoggerFactory.getLogger(BatchDocu.class);
    
    /**
     * Main treatment.
     * @param args
     *      no arguments
     */
    public static void main(String[] args) throws Exception {
        
        // Inputs
        String folderPath = "D:/Documentaires";
       
        long start = System.currentTimeMillis();
        AnnotationConfigApplicationContext  appCtx = new AnnotationConfigApplicationContext();
        appCtx.register(CommonsConfig.class);
        appCtx.refresh();
       
        DocuServices batch = appCtx.getBean(DocuServices.class);
        
        File rootFolder = new File(folderPath);
        
        // (A) Le répertoires, existe et on peut le lire
        batch.checkInputFolder(rootFolder);
        logger.info("Repertoire '" + folderPath + "' trouvé");
        
        // (B) Verification des genres
        ComparaisonFsDb syncLevel1 = batch.checkGenres(rootFolder);
        if (!syncLevel1.isValid()) {
            logger.error("Genres invalides" + syncLevel1.toString());
            System.exit(-1);
        }
        logger.info("Les genres sont synchronisés");
        
        // (C) Verification des sous-genres
        Arrays.stream(rootFolder.listFiles(File::isDirectory)). //
            collect(Collectors.toCollection(TreeSet::new)).stream().//
            forEach(lvl1 -> {
              ComparaisonFsDb syncLevel2 = batch.checkSubGenres(lvl1);
              if (!syncLevel2.isValid()) {
                logger.error("SousGenres invalides dans '" + lvl1 + "' " + syncLevel2.toString());
                System.exit(-2);
              }
            }
        );
        logger.info("Les sous-genres sont synchronisés");
        
        /* (D) Analyse des documentaires genre par genre
        Arrays.stream(rootFolder.listFiles(File::isDirectory)). //
            collect(Collectors.toCollection(TreeSet::new)).stream().//
            forEach(file -> batch.analyseRepertoire(file));
        */
        batch.analyseRepertoire(new File("D:\\Documentaires\\Astronomie"));
        
        long end = System.currentTimeMillis();
        logger.info("Traitement Terminé en " + (end-start) + " millis.");
        
        appCtx.close();
    }

}
