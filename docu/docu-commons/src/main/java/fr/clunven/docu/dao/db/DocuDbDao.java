package fr.clunven.docu.dao.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import fr.clunven.docu.domain.Documentaire;
import fr.clunven.docu.domain.Episode;

/**
 * Implementation of {@link FeatureStore} to work with RDBMS through JDBC.
 * 
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
@Repository("db.dao.docu")
public class DocuDbDao extends AbstractDaoSupport {
   
    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(DocuDbDao.class);
    
    public boolean isSerieExist(String name) {
        return getJdbcTemplate().queryForObject(
                "SELECT COUNT(*) FROM t_serie WHERE UPPER(TITRE) LIKE ?", 
                Integer.class, name.toUpperCase().trim()) > 0;
    }
   
    public boolean isDocumentaireExist(String titre) {
        return getJdbcTemplate().queryForObject(
                "SELECT COUNT(*) FROM t_documentaire WHERE UPPER(TITRE) LIKE ?", 
                Integer.class, titre.toUpperCase().trim()) > 0;
    }
    
    public boolean isEpisodeExist(String titre, int serie, int saison, int nb) {
        return getJdbcTemplate().queryForObject(
                "SELECT COUNT(*) FROM t_episode " + 
                "WHERE (SERIE = ?) and (SAISON = ?) AND (EPISODE = ?) AND (UPPER(TITRE) LIKE ?)", 
                Integer.class,serie, saison, nb, titre.toUpperCase().trim()) > 0;
    }
   
    public int searchGenreIdByGenreName(String genreNom) {
        try {
            return getJdbcTemplate().queryForObject("SELECT ID FROM ref_genre WHERE NOM LIKE ?", 
                            Integer.class, genreNom.trim());
        } catch (EmptyResultDataAccessException re) {
            throw new IllegalArgumentException("Cannot find GENRE : " + genreNom, re);
        }
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
    
    /**
     * Create episode dans la base.
     *
     * @param ep
     *     documentaire à insérer
     */
    public void createEpisode(Episode ep) {
       if (!isEpisodeExist(ep.getTitre() , ep.getSerie(),  ep.getSaison(), ep.getEpisode())) {
           
           StringBuilder sqlQuery = new StringBuilder();
           sqlQuery.append("INSERT INTO t_episode (ID, TITRE, TITRE_ORIGINAL, DESCRIPTION,");
           sqlQuery.append("REALISATEUR, ANNEE, DUREE, IMAGE, LANGUE, SOUSTITRES, NOTE, VU,");
           sqlQuery.append("TAILLE, FORMAT, BITRATE, QUALITE, RESOLUTION, SERIE, SAISON, EPISODE)");
           sqlQuery.append("VALUES (NULL, ?, '', '', '', ?, ?, ?, 'FR', NULL, NULL, '0', ?, ?, ?, '0', ?, ?, ?, ?)");
           getJdbcTemplate().update(sqlQuery.toString(), ep.getTitre(), 
                   ep.getAnnee(), ep.getDuree() / 1000 / 60, 
                   getSerieImage(ep.getSerie()), ep.getTaille() /1024 / 1024,
                   ep.getFormatCode(), ep.getBitrate(), ep.getResolution(), ep.getSerie(),
                   ep.getSaison(), ep.getEpisode());
           logger.info("[CREATION] " + ep.getTitre() 
           + "' : annee=" + ep.getAnnee() + " duree=" + ep.getDuree() + 
           " episode=" + ep.getEpisode() + " saison=" + ep.getSaison() +
           " serie=" + ep.getSerie() + " format=" + ep.getFormat() +
           " resolution=" + ep.getResolution() + " bitrate=" + ep.getBitrate() + " taille=" + ep.getTaille());
       } else {
           logger.debug("[OK] " + ep.getTitre());
       }
    }
    
    /**
     * Check that resolution is not null.
     *
     * @param titre
     *      title
     * @return
     *      is the documentaire already processed
     */
    public boolean isDocumentaireResolution(String titre) {
        return getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM t_documentaire WHERE UPPER(TITRE) LIKE ? AND RESOLUTION IS NULL", 
                Integer.class, titre.toUpperCase().trim()) > 0;
    }
    
    /**
     * Get the id from titre.
     *
     * @param titre
     *      titre to be found
     * @return
     *      return the id is exist
     */
    public int getDocumentaireId(String titre) {
        try {
            return getJdbcTemplate().
                    queryForObject(  "SELECT ID FROM t_documentaire "
                                   + "WHERE UPPER(TITRE) LIKE ?", Integer.class, titre.toUpperCase().trim());
        } catch(EmptyResultDataAccessException re) {
            logger.error("Cannot find" + titre, re);
            throw new IllegalArgumentException("Cannot find DOCUMENTAIRE : " + titre , re);
        }
    }
    
    /**
     * Creation du documentaire si non existant.
     *
     * @param ep
     *      le documentaire en cours
     */
    public void createDocumentaire(Documentaire ep) {
        if (!isDocumentaireExist(ep.getTitre())) {
            
            StringBuilder sqlQuery = new StringBuilder();
            sqlQuery.append("INSERT INTO t_documentaire (ID, TITRE, TITRE_ORIGINAL, DESCRIPTION,");
            sqlQuery.append("REALISATEUR, ANNEE, DUREE, IMAGE, GENRE, PAYS, LANGUE, SOUSTITRES, NOTE, VU,");
            sqlQuery.append("TAILLE, FORMAT, BITRATE, QUALITE, RESOLUTION)");
            sqlQuery.append("VALUES (NULL, ?, '', '', '', ?, ?, '', ?, NULL, 'FR', NULL, NULL, '0', ?, ?, ?, '0', ?)");
            getJdbcTemplate().update(sqlQuery.toString(), ep.getTitre(), 
                    ep.getAnnee(), ep.getDuree() / 1000 / 60, 
                    ep.getGenre(),
                    ep.getTaille() /1024 / 1024,
                    ep.getFormatCode(), 
                    ep.getBitrate(), 
                    ep.getResolution());
            logger.info("[CREATION] " + ep.getTitre()
            + "' : annee=" + ep.getAnnee() + " duree=" + ep.getDuree() + 
            " format=" + ep.getFormat() +
            " resolution=" + ep.getResolution() + " bitrate=" + ep.getBitrate());
            
        } else if (isDocumentaireResolution(ep.getTitre())) {
            // La resolution est NULL updating....
            getJdbcTemplate().update("UPDATE t_documentaire SET ANNEE=?,DUREE=?,TAILLE=?,FORMAT=?,BITRATE=?,RESOLUTION=? WHERE ID = ?",
                    ep.getAnnee(), ep.getDuree() / 1000 / 60,  
                    ep.getTaille() /1024 / 1024,
                    ep.getFormatCode(), 
                    ep.getBitrate(), 
                    ep.getResolution(),
                    getDocumentaireId(ep.getTitre()));
            
            logger.info("[UPDATE] " + ep.getTitre()
            + "' : annee=" + ep.getAnnee() + " duree=" + ep.getDuree() + 
            " format=" + ep.getFormat() +
            " resolution=" + ep.getResolution() + " bitrate=" + ep.getBitrate());
        } else {
            logger.debug("[OK] " +  ep.getTitre());
        }
    }
    
    
    /** List all genres and put in the cache. */
    public List < String > getListOfGenres() {
        if (getGenres().isEmpty()) {
            getGenres().addAll(
                    getJdbcTemplate().query("SELECT NOM FROM ref_genre WHERE PARENT IS NULL order by NOM ASC", new SingleColumnRowMapper<String>()));
        }
        return getGenres();
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

  

}
