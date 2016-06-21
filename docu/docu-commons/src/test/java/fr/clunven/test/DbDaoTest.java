package fr.clunven.test;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import fr.clunven.docu.dao.db.ReferentialDbDao;
import fr.clunven.docu.dao.db.SerieDbDao;
import fr.clunven.docu.dao.db.UserDbDao;
import fr.clunven.docu.dao.db.dto.GenreDto;
import fr.clunven.docu.dao.db.dto.SubGenreDto;
import fr.clunven.docu.dao.db.dto.UserDto;

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
    private ReferentialDbDao dbDao;
    
    @Autowired
    private UserDbDao userDbDao;
    
    @Autowired
    private ReferentialDbDao refDbDao;
    
    @Autowired
    private SerieDbDao serieDbDao;
    
    @Test
    public void testRef() {
        Map < GenreDto, List < SubGenreDto > > elemets = refDbDao.getMenu();
        for (Entry<GenreDto, List < SubGenreDto >>  entry : elemets.entrySet()) {
            System.out.println(entry.getKey().getName());
            System.out.println(entry.getValue());
        }
    }
    
    @Test
    public void testSerie() {
        for(SubGenreDto dto : serieDbDao.getSerieMenu()) {
            System.out.println(dto.getSubgenre().getName() + "-" + dto.getNb());
        }
    }
    
    @Test
    public void testDbConnect() {
        System.out.println(userDbDao.exist("clu"));
        
       UserDto dto = userDbDao.loadUserByUid("clu");
       System.out.println(dto.getPassword());
    }
    
    @Test
    public void testReferentials() {
        dbDao.getFormats();
        dbDao.getLangues();
        dbDao.getPays();
    }
      
}
