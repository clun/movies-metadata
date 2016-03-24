package fr.clunven.docu;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.clunven.docu.dao.db.ReferentialDbDao;


public class SampleMain {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext  ctx = new AnnotationConfigApplicationContext();
        ctx.register(CommonsConfig.class);
        ctx.refresh();
        
        ReferentialDbDao dbDao = (ReferentialDbDao) ctx.getBean(ReferentialDbDao.class);
        System.out.println(dbDao.getGenres());
        
    }

}
