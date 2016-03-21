package fr.clunven.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fr.clunven.docu.CommonsConfig;
import fr.clunven.docu.dao.db.DocuDbDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={CommonsConfig.class})
public class DbDaoTest {
    
    @Configuration
    @ComponentScan("fr.clunven.docu")
    @PropertySource("classpath:docu-conf.properties")
    @EnableTransactionManagement
    @Import({CommonsConfig.class})
    public static class TestConfig {
        
        @Bean
        public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }
    
    @Autowired
    private DocuDbDao dbDao;
    
    @Test
    public void testDbConnect() {
        System.out.println(dbDao.getListOfGenres());
    }
      
}
