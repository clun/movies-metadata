package fr.clunven.docu;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Implementation to work with RDBMS through JDBC.
 * 
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
@Configuration
@ComponentScan("fr.clunven.docu")
@PropertySource("classpath:docu-conf.properties")
@EnableTransactionManagement
public class CommonsConfig {
    
    @Autowired
    private Environment env;
   
    /*@Bean
    public DataSource driverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.sql.driverclassName"));
        dataSource.setUrl(env.getRequiredProperty("db.sql.url"));
        dataSource.setUsername(env.getRequiredProperty("db.sql.username"));
        dataSource.setPassword(env.getRequiredProperty("db.sql.password"));
        Properties props = new Properties();
        props.setProperty("useUnicode", "yes");
        props.setProperty("characterEncoding", "UTF-8");
        dataSource.setConnectionProperties(props);
        return dataSource;
    }*/

    @Bean
    public DataSource basicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.sql.driverclassName"));
        dataSource.setUrl(env.getRequiredProperty("db.sql.url"));
        dataSource.setUsername(env.getRequiredProperty("db.sql.username"));
        dataSource.setPassword(env.getRequiredProperty("db.sql.password"));
        dataSource.setConnectionProperties("useUnicode=yes;characterEncoding=UTF-8;");
        return dataSource;
    }

    /*
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(basicDataSource());
    }*/
}