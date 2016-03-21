package fr.clunven.docu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.clunven.docu.dao.db.DocuDbDao;


public class SampleMain {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext  ctx = new AnnotationConfigApplicationContext();
        ctx.register(CommonsConfig.class);
        ctx.refresh();
        
        DocuDbDao dbDao = (DocuDbDao) ctx.getBean(DocuDbDao.class);
        System.out.println(dbDao.getListOfGenres());
        
    }

}
