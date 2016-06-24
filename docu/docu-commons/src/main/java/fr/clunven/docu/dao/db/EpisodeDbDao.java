package fr.clunven.docu.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.clunven.docu.dao.db.rowmapper.EpisodeRowMapper;
import fr.clunven.docu.domain.Episode;

@Repository("docu.dao.db.episode")
public class EpisodeDbDao extends AbstractDaoSupport {
    
    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(EpisodeDbDao.class);
    
    /** Row Mapper instance. */
    private EpisodeRowMapper EP_ROW_MAPPER = new EpisodeRowMapper();
    
    /** Check episode exist. */
    public boolean exist(String titre, int serie, int saison, int nb) {
        return getJdbcTemplate().queryForObject(
                "SELECT COUNT(*) FROM t_episode " + 
                "WHERE (SERIE = ?) and (SAISON = ?) AND (EPISODE = ?) AND (UPPER(TITRE) LIKE ?)", 
                Integer.class,serie, saison, nb, titre.toUpperCase().trim()) > 0;
    }
    
    public boolean existID(int serie, int saison, int nb) {
        return getJdbcTemplate().queryForObject(
                "SELECT COUNT(*) FROM t_episode WHERE (SERIE = ?) and (SAISON = ?) AND (EPISODE = ?)", 
                Integer.class, serie, saison, nb) > 0;
    }
    
    public Episode getEpisodeById(int serie, int saison, int nb) {
        return getJdbcTemplate().queryForObject(
                "SELECT * FROM t_episode WHERE (SERIE = ?) and (SAISON = ?) AND (EPISODE = ?)", 
                EP_ROW_MAPPER, serie, saison, nb);
    }
    
    /**
     * Create episode dans la base.
     *
     * @param ep
     *     documentaire à insérer
     */
    public void createEpisode(Episode ep, String serieImage) {
       if (!existID(ep.getSerie(),  ep.getSaison(), ep.getEpisode())) {
           StringBuilder sqlQuery = new StringBuilder();
           sqlQuery.append("INSERT INTO t_episode (ID, TITRE, TITRE_ORIGINAL, DESCRIPTION,");
           sqlQuery.append("REALISATEUR, ANNEE, DUREE, IMAGE, LANGUE, SOUSTITRES, NOTE, VU,");
           sqlQuery.append("TAILLE, FORMAT, BITRATE, QUALITE, RESOLUTION, SERIE, SAISON, EPISODE)");
           sqlQuery.append("VALUES (NULL, ?, '', '', '', ?, ?, ?, 'FR', NULL, NULL, '0', ?, ?, ?, '0', ?, ?, ?, ?)");
           getJdbcTemplate().update(sqlQuery.toString(), ep.getTitre(), 
                   ep.getAnnee(), ep.getDuree() / 1000 / 60, 
                   serieImage, ep.getTaille() / 1024 / 1024,
                   ep.getFormatCode(), ep.getBitrate(), ep.getResolution(), ep.getSerie(),
                   ep.getSaison(), ep.getEpisode());
       } else {
           logger.debug(" -> Already exist");
       }
    }
    
    public void updateEpisodeFileInformation(Episode ep) {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("UPDATE t_episode SET DUREE= ?, TAILLE= ?, FORMAT= ?, BITRATE= ?, QUALITE=?, RESOLUTION=? ");
        sqlQuery.append("WHERE (SERIE=?) AND (SAISON=?) AND (EPISODE=?)");
        getJdbcTemplate().update(sqlQuery.toString(), ep.getDuree(), ep.getTaille(), 
                ep.getFormatCode(), ep.getBitrate(), ep.getQualite(), ep.getResolution(),
                ep.getSerie(), ep.getSaison(), ep.getEpisode());
    }

}
