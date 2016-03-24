package fr.clunven.docu.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.clunven.docu.domain.Episode;

@Repository("docu.dao.db.episode")
public class EpisodeDbDao extends AbstractDaoSupport {
    
    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(EpisodeDbDao.class);
    
    public boolean isEpisodeExist(String titre, int serie, int saison, int nb) {
        return getJdbcTemplate().queryForObject(
                "SELECT COUNT(*) FROM t_episode " + 
                "WHERE (SERIE = ?) and (SAISON = ?) AND (EPISODE = ?) AND (UPPER(TITRE) LIKE ?)", 
                Integer.class,serie, saison, nb, titre.toUpperCase().trim()) > 0;
    }
    
    /**
     * Create episode dans la base.
     *
     * @param ep
     *     documentaire à insérer
     */
    public void createEpisode(Episode ep, String serieImage) {
       if (!isEpisodeExist(ep.getTitre() , ep.getSerie(),  ep.getSaison(), ep.getEpisode())) {
           
           StringBuilder sqlQuery = new StringBuilder();
           sqlQuery.append("INSERT INTO t_episode (ID, TITRE, TITRE_ORIGINAL, DESCRIPTION,");
           sqlQuery.append("REALISATEUR, ANNEE, DUREE, IMAGE, LANGUE, SOUSTITRES, NOTE, VU,");
           sqlQuery.append("TAILLE, FORMAT, BITRATE, QUALITE, RESOLUTION, SERIE, SAISON, EPISODE)");
           sqlQuery.append("VALUES (NULL, ?, '', '', '', ?, ?, ?, 'FR', NULL, NULL, '0', ?, ?, ?, '0', ?, ?, ?, ?)");
           getJdbcTemplate().update(sqlQuery.toString(), ep.getTitre(), 
                   ep.getAnnee(), ep.getDuree() / 1000 / 60, 
                   serieImage, ep.getTaille() /1024 / 1024,
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

}
