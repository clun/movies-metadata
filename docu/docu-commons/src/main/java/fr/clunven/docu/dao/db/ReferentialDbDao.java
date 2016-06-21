package fr.clunven.docu.dao.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import fr.clunven.docu.dao.db.dto.FormatDto;
import fr.clunven.docu.dao.db.dto.GenreDto;
import fr.clunven.docu.dao.db.dto.LangueDto;
import fr.clunven.docu.dao.db.dto.PaysDto;
import fr.clunven.docu.dao.db.dto.SubGenreDto;
import fr.clunven.docu.dao.db.rowmapper.FormatRowMapper;
import fr.clunven.docu.dao.db.rowmapper.GenreRowMapper;
import fr.clunven.docu.dao.db.rowmapper.LangueRowMapper;
import fr.clunven.docu.dao.db.rowmapper.PaysRowMapper;
import fr.clunven.docu.dao.db.rowmapper.SubGenreRowMapper;

@Repository("docu.dao.db.ref")
public class ReferentialDbDao extends AbstractDaoSupport {
    
    private static final String QUERY_GENRE_TOPLEVEL = 
            "SELECT * FROM ref_genre WHERE PARENT IS NULL order by NOM ASC";
    
    private static final String QUERY_GENRE_SUBLEVEL = 
              "SELECT D.Genre AS ID, G.NOM AS NOM, G.ICONE AS ICONE, G.PARENT AS PARENT, COUNT( * ) AS NB "
            + "FROM t_documentaire D, ref_genre G "
            + "WHERE D.GENRE = G.ID  "
            + "AND (PARENT = ? OR GENRE = ? ) "
            + "GROUP BY D.Genre "
            + "ORDER BY G.PARENT, G.NOM ASC";
    
    private static final String QUERY_ALL_FORMAT = "SELECT * FROM ref_format";

    private static final String QUERY_ALL_LANGUE = "SELECT * FROM ref_langue";

    private static final String QUERY_ALL_PAYS = "SELECT * FROM ref_pays";
    
    private static final GenreRowMapper GENRE_ROWMAPPER = new GenreRowMapper();
    
    private static final SubGenreRowMapper SUBGENRE_ROWMAPPER = new SubGenreRowMapper();
    
    private static final FormatRowMapper ROWMAPPER_FORMAT = new FormatRowMapper();
    
    private static final LangueRowMapper ROWMAPPER_LANGUE = new LangueRowMapper();
    
    private static final PaysRowMapper ROWMAPPER_PAYS = new PaysRowMapper();
    
    
    /** Cache of list of genres. */
    private List < String > genres = new ArrayList<String>();
    
    /** Cache of list of subgenres. */
    private Map < Integer, List <String> > childGenres = new HashMap<>();
    
    private Map < String, FormatDto > refFormats = new HashMap<>();
    
    private Map < String, LangueDto > refLangues = new HashMap<>();
    
    private Map < String, PaysDto > refPays = new HashMap<>();
   
    // -- Operations les IHM 
    
    public List < GenreDto > getListGenreTopLevel() {
        return getJdbcTemplate().query(QUERY_GENRE_TOPLEVEL, GENRE_ROWMAPPER);
    }
    
    public Set < String > getSetOfTopLevelGenreName() {
        return getListGenreTopLevel().stream().map(GenreDto::getName).collect(Collectors.toCollection(TreeSet::new));
    }
    
    public List < SubGenreDto > getListGenreSubLevel(int parent, String genre) {
        return getJdbcTemplate().query(QUERY_GENRE_SUBLEVEL, SUBGENRE_ROWMAPPER, parent, parent);
    }
    
    public Map < GenreDto, List < SubGenreDto > > getMenu() {
        Map < GenreDto, List < SubGenreDto > >  menu = new TreeMap<>();
        getListGenreTopLevel().stream().forEach(g -> menu.put(g, getListGenreSubLevel(g.getId(), g.getName())));
        return menu;
    }
    
    /**
     * Lire la liste des formats. 
     */
    public Map < String, FormatDto> getFormats() {
        if (this.refFormats.isEmpty()) {
            List < FormatDto > foms = getJdbcTemplate().query(QUERY_ALL_FORMAT, ROWMAPPER_FORMAT);
            for (FormatDto formatDto : foms) {
                refFormats.put(formatDto.getExtension(), formatDto);
            }
        }
        return refFormats;
    }
    
    /**
     * Lecture du bean depuis l'extension.
     */
    public FormatDto getFormat(String extention) {
        return getFormats().get(extention);
    }
    
    public Map < String, LangueDto > getLangues() {
        if (this.refLangues.isEmpty()) {
            List < LangueDto > foms = getJdbcTemplate().query(QUERY_ALL_LANGUE, ROWMAPPER_LANGUE);
            for (LangueDto dto : foms) {
                refLangues.put(dto.getCode(), dto);
            }
        }
        return refLangues;
    }
    
    public LangueDto getLangue(String code) {
        return getLangues().get(code);
    }
    
    /**
     * Lire la liste des formats. 
     */
    public Map < String, PaysDto> getPays() {
        if (this.refPays.isEmpty()) {
            List < PaysDto > foms = getJdbcTemplate().query(QUERY_ALL_PAYS, ROWMAPPER_PAYS);
            for (PaysDto dto : foms) {
                refPays.put(dto.getNom(), dto);
            }
        }
        return refPays;
    }
    
    public PaysDto getPays(String nom) {
        return getPays().get(nom);
    }
    
    public int searchGenreIdByGenreName(String genreNom) {
        try {
            return getJdbcTemplate().queryForObject("SELECT ID FROM ref_genre WHERE NOM LIKE ?", 
                            Integer.class, genreNom.trim());
        } catch (EmptyResultDataAccessException re) {
            throw new IllegalArgumentException("Cannot find GENRE : " + genreNom, re);
        }
    }
    
    public GenreDto getGenreById(int idgenre) {
        try {
            return getJdbcTemplate().queryForObject(
                    "SELECT * FROM ref_genre WHERE ID LIKE ?", GENRE_ROWMAPPER, idgenre);
        } catch (EmptyResultDataAccessException re) {
            throw new IllegalArgumentException("Cannot find GENRE : " + idgenre, re);
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
