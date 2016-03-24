package fr.clunven.docu.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Operations sur les series.
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
@Repository("docu.dao.db.serie")
public class SerieDbDao extends AbstractDaoSupport {
    
    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(SerieDbDao.class);
    
    public boolean isSerieExist(String name) {
        return getJdbcTemplate().queryForObject(
                "SELECT COUNT(*) FROM t_serie WHERE UPPER(TITRE) LIKE ?", 
                Integer.class, name.toUpperCase().trim()) > 0;
    }

    public int getSerieIdBySerieName(String serieName) {
        try {
            return getJdbcTemplate().queryForObject("SELECT ID FROM t_serie WHERE UPPER(TITRE) LIKE ?", 
                            Integer.class, serieName.toUpperCase().trim());
        } catch(EmptyResultDataAccessException re) {
            throw new IllegalArgumentException("Cannot find SERIE : " + serieName , re);
        }
    }
    
    
    /**
     * Insertion into serie DB.
     * 
     * @param titre
     *      title
     * @param genre
     *      target genre
     * @param annee1
     *      target annee
     * @param anne2
     *      target annee2
     * @param vo
     *      target vo
     */
    public void createSerie(String titre, int genre, int annee1, int anne2, boolean vo) {
        getJdbcTemplate().update(""
                + "INSERT INTO t_serie (ID, TITRE, TITRE_ORIGINAL, DESCRIPTION, IMAGE, ANNEE_DEBUT, ANNEE_FIN, GENRE, PAYS) "
                + "VALUES (NULL, ?, '', '', '', ?, ?, ?, NULL)", titre.trim(), annee1, anne2, genre);
    }
    

    /**
     * Recherche image de la serie pour init.
     *
     * @param serieid
     *      identifiant de la série
     * @return
     *      l'image en question
     */
    public String getSerieImage(int serieid) {
        return getJdbcTemplate().queryForObject("SELECT IMAGE FROM t_serie " + "WHERE ID = ?", String.class, serieid);
    }
    
   
    
}
