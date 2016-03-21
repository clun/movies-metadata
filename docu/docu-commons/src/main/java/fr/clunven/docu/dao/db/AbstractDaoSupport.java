package fr.clunven.docu.dao.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Superclass to work with DB.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public abstract class AbstractDaoSupport {
    
    /** SQL DataSource. */
    @Autowired
    private DataSource dataSource;
    
    /** Access to storage. */
    private JdbcTemplate jdbcTemplate;
    
    /** Cache of list of genres. */
    private List < String > genres = new ArrayList<String>();
    
    /** Cache of list of subgenres. */
    private Map < Integer, List <String> > childGenres = new HashMap<>();
    
    /**
     * @param dataSource
     *            the dataSource to set
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
   
    /**
     * Getter accessor for attribute 'jdbcTemplate'.
     * 
     * @return current value of 'jdbcTemplate'
     */
    public JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            if (dataSource == null) {
                throw new IllegalStateException("ff4j-jdbc: DatabaseStore has not been properly initialized, datasource is null");
            }
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    /**
     * Getter accessor for attribute 'genres'.
     *
     * @return
     *       current value of 'genres'
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Setter accessor for attribute 'genres'.
     * @param genres
     * 		new value for 'genres '
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Getter accessor for attribute 'childGenres'.
     *
     * @return
     *       current value of 'childGenres'
     */
    public Map<Integer, List<String>> getChildGenres() {
        return childGenres;
    }

    /**
     * Setter accessor for attribute 'childGenres'.
     * @param childGenres
     * 		new value for 'childGenres '
     */
    public void setChildGenres(Map<Integer, List<String>> childGenres) {
        this.childGenres = childGenres;
    }

    /**
     * Getter accessor for attribute 'dataSource'.
     *
     * @return
     *       current value of 'dataSource'
     */
    public DataSource getDataSource() {
        return dataSource;
    }

}
