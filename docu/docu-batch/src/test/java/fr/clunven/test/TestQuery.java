package fr.clunven.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.clunven.docu.dao.DocuDbDao;
import fr.clunven.docu.domain.SmartSerieName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-docus.xml")
public class TestQuery {
    

    @Test
    public void testDocu() {
        /*String expression1 = "01 - Today is the gread day (2001).mp4";
        String expression2 = "02 - Today is the gread day (2002) (vo).mp4";
        String expression3 = "02 - Today is the gread day (2003)(vo)";
        
        SmartFileName sfm3 = new SmartFileName(expression3);
        System.out.println("1: annee=" + sfm3.getAnnee() 
                + " vo=" + sfm3.isVo() + " fileName=" + sfm3.getTitre() 
                + " extension=" + sfm3.getExtension() + " number=" + sfm3.getNumber());
      
        
        SmartFileName sfm2 = new SmartFileName(expression2);
        System.out.println("1: annee=" + sfm2.getAnnee() 
                + " vo=" + sfm2.isVo() + " fileName=" + sfm2.getTitre() + " extension=" + sfm2.getExtension());
        
        SmartFileName sfm = new SmartFileName(expression1);
        System.out.println("1: annee=" + sfm.getAnnee() 
                + " vo=" + sfm.isVo() + " fileName=" + sfm.getTitre() + " extension=" + sfm.getExtension());
     
        SmartFileName sfm4 = new SmartFileName("Saison 4 (2002)");
        System.out.println("1: annee=" + sfm4.getAnnee() 
                + " vo=" + sfm4.isVo() + " fileName=" + sfm4.getTitre() + " extension=" + sfm4.getExtension());
          
        SmartFileName sfm5 = new SmartFileName("The superdocumentaire (1999)");
        System.out.println("1: annee=" + sfm5.getAnnee() 
                + " vo=" + sfm5.isVo() + " fileName=" + sfm5.getTitre() + " extension=" + sfm5.getExtension());
        */
        SmartSerieName ssn = new SmartSerieName("[SERIE] - FondationCousteau (1962-1970)");
        System.out.println(ssn.getAnneeStart());
        System.out.println(ssn.getAnneeEnd());
        
        
        SmartSerieName ssn2 = new SmartSerieName("[SERIE] - FondationCousteau (2001)");
        System.out.println(ssn2.getAnneeStart());
        System.out.println(ssn2.getAnneeEnd());
        
        System.out.println(2855080 / 1000 / 60);
         
        
    }
    

}
