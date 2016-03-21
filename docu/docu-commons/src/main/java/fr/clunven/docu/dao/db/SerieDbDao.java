package fr.clunven.docu.dao.db;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Operations sur les series.
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
@Repository("db.dao.db.serie")
public class SerieDbDao {
    
    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(DocuDbDao.class);
    
    /** SQL DataSource. */
    @Autowired
    private DataSource dataSource;
    
    /** Access to storage. */
    private JdbcTemplate jdbcTemplate;
    
    

}
