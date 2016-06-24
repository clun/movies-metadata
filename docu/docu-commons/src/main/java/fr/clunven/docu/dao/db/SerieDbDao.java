package fr.clunven.docu.dao.db;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import fr.clunven.docu.dao.db.dto.SerieDto;
import fr.clunven.docu.dao.db.dto.SubGenreDto;
import fr.clunven.docu.dao.db.rowmapper.SerieRowMapper;
import fr.clunven.docu.dao.db.rowmapper.SubGenreRowMapper;

/**
 * Operations sur les series.
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
@Repository("docu.dao.db.serie")
public class SerieDbDao extends AbstractDaoSupport {

    /** Rowmapper. */
    private static final SubGenreRowMapper ROWMAPPER_SUBGENRE = new SubGenreRowMapper();

    /** Rowmapper. */
    private static final SerieRowMapper ROWMAPPER_SERIE = new SerieRowMapper();
    
    /** SQL Query. */
    private static final String QUERY_GETID_BYNAME = 
            "SELECT ID FROM t_serie WHERE UPPER(TITRE) LIKE ?";

    /** Menu Serie. */
    private static String QUERY_MENU_SERIE = 
              "SELECT S.Genre AS ID, G.NOM AS NOM, G.ICONE as ICONE, 0 AS PARENT, COUNT( * ) AS NB "
            + "FROM t_serie S, ref_genre G "
            + "WHERE S.GENRE = G.ID "
            + "GROUP BY S.Genre "
            + "ORDER BY G.NOM ASC";
    
    /** Serie exist with name. */
    private static String QUERY_EXIST_SERIE = 
              "SELECT COUNT(*) FROM t_serie "
            + "WHERE UPPER(TITRE) LIKE ?";
    
    /** Serie exist with name. */
    private static String QUERY_BYGENRE = "SELECT * FROM t_serie WHERE genre = ? order by titre";
    
    /** Serie exist with name. */
    private static String QUERY_BYGENRE_TITRE = "SELECT TITRE FROM t_serie WHERE genre = ? order by titre";
    
    /** Serie exist with name. */
    private static String QUERY_BYID = "SELECT * FROM t_serie WHERE id = ?";
    
    /** Check existence from serie name (Creation Batch). */
    public boolean exist(String name) {
        return getJdbcTemplate().queryForObject(QUERY_EXIST_SERIE, Integer.class, 
                name.toUpperCase().trim()) > 0;
    }

    /** Menu for serie. */
    public List < SubGenreDto > getSerieMenu() {
        return getJdbcTemplate().query(QUERY_MENU_SERIE, ROWMAPPER_SUBGENRE);
    }

    /** Get of Serie for each genre. */
    public List < SerieDto > getSeriesByGenre(int genre) {
        return getJdbcTemplate().query(QUERY_BYGENRE, ROWMAPPER_SERIE, genre);
    }
    
    public Set < String > getSerieNamesByGenre(int genre) {
        return new TreeSet<String>(getJdbcTemplate().query(QUERY_BYGENRE_TITRE, 
                new SingleColumnRowMapper<String>(), genre));
    }
    
    public SerieDto getSerieById(int id) {
        return getJdbcTemplate().queryForObject(QUERY_BYID, ROWMAPPER_SERIE, id);
    }

    public int getSerieIdByName(String serieName) {
        try {
            return getJdbcTemplate().queryForObject(QUERY_GETID_BYNAME,
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
