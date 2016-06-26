package fr.clunven.docu;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.clunven.docu.service.BatchConfig;
import fr.clunven.docu.service.BatchServices;

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
        AnnotationConfigApplicationContext  appCtx = new AnnotationConfigApplicationContext();
        appCtx.register(CommonsConfig.class);
        appCtx.register(BatchConfig.class);
        appCtx.refresh();
       
        long start = System.currentTimeMillis();
        BatchServices batch = appCtx.getBean(BatchServices.class);
        batch.setModeSimulation(false);
        
        //batch.run("D:/Documentaires");
        
        batch.analyseRepertoire(new File("D:/Documentaires/Animalier"));
        
        // Execution Time
        long executionTime = System.currentTimeMillis() - start;
        logger.info("Traitement Terminé en " + batch.formatExecutionTime(executionTime));
        appCtx.close();
    }

}
