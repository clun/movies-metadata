package fr.clunven.docu.dao.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import fr.clunven.docu.dao.db.dto.GenreDto;
import fr.clunven.docu.dao.db.dto.SubGenreDto;
import fr.clunven.docu.dao.db.rowmapper.GenreRowMapper;
import fr.clunven.docu.dao.db.rowmapper.SubGenreRowMapper;

@Repository("docu.dao.db.ref")
public class ReferentialDbDao extends AbstractDaoSupport {
    
    private static final String QUERY_GENRE_TOPLEVEL = "SELECT * FROM ref_genre WHERE PARENT IS NULL order by NOM ASC";
    
    private static final String QUERY_GENRE_SUBLEVEL = 
              "SELECT D.Genre AS ID, G.NOM AS NOM, G.ICONE AS ICONE, G.PARENT AS PARENT, COUNT( * ) AS NB "
            + "FROM t_documentaire D, ref_genre G "
            + "WHERE D.GENRE = G.ID  "
            + "AND (PARENT = ? OR GENRE = ? ) "
            + "GROUP BY D.Genre "
            + "ORDER BY G.PARENT, G.NOM ASC";
    
    private static final GenreRowMapper GENRE_ROWMAPPER = new GenreRowMapper();
    
    private static final SubGenreRowMapper SUBGENRE_ROWMAPPER = new SubGenreRowMapper();
    
    /** Cache of list of genres. */
    private List < String > genres = new ArrayList<String>();
    
    /** Cache of list of subgenres. */
    private Map < Integer, List <String> > childGenres = new HashMap<>();
    
    // Services Web
    
    public List < GenreDto > getListGenreTopLevel() {
        return getJdbcTemplate().query(QUERY_GENRE_TOPLEVEL, GENRE_ROWMAPPER);
    }
    
    public List < SubGenreDto > getListGenreSubLevel(int parent, String genre) {
        return getJdbcTemplate().query(QUERY_GENRE_SUBLEVEL, SUBGENRE_ROWMAPPER, parent, parent);
    }
    
    public Map < GenreDto, List < SubGenreDto > > getMenu() {
        Map < GenreDto, List < SubGenreDto > >  menu = new TreeMap<>();
        getListGenreTopLevel().stream().forEach(g -> menu.put(g, getListGenreSubLevel(g.getId(), g.getName())));
        return menu;
    }    
    
    
    
    public int searchGenreIdByGenreName(String genreNom) {
        try {
            return getJdbcTemplate().queryForObject("SELECT ID FROM ref_genre WHERE NOM LIKE ?", 
                            Integer.class, genreNom.trim());
        } catch (EmptyResultDataAccessException re) {
            throw new IllegalArgumentException("Cannot find GENRE : " + genreNom, re);
        }
    }
    
    /**
     * Search fol children of a dedicated genre.
     */
    public List < String > getListOfChildGenre(int parent) {
        if (!getChildGenres().containsKey(parent)) {
            // Filling children
            getChildGenres().put(parent, 
                    getJdbcTemplate().query("SELECT NOM from ref_genre WHERE PARENT = ?" , new SingleColumnRowMapper<String>(), parent));
        }
        return getChildGenres().get(parent);
    }

    /**
     * Getter accessor for attribute 'genres'.
     *
     * @return
     *       current value of 'genres'
     */
    public List<String> getGenres() {
        if (genres.isEmpty()) {
            genres.addAll(
                    getJdbcTemplate().query("SELECT NOM FROM ref_genre WHERE PARENT IS NULL order by NOM ASC", new SingleColumnRowMapper<String>()));
        }
        return genres;
    }

    /**
     * Setter accessor for attribute 'genres'.
     * @param genres
     *      new value for 'genres '
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
     *      new value for 'childGenres '
     */
    public void setChildGenres(Map<Integer, List<String>> childGenres) {
        this.childGenres = childGenres;
    }


}
