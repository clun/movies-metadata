package fr.clunven.docu.dao.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.domain.Episode;

/**
 * Mapper for episode
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class EpisodeRowMapper implements RowMapper < Episode > {

    /** {@inheritDoc} */
    public Episode mapRow(ResultSet rs, int rowNum) throws SQLException {
        Episode dto = new Episode();
        dto.setTitre(rs.getString("TITRE"));
        dto.setAnnee(rs.getInt("ANNEE"));
        dto.setBitrate(rs.getLong("BITRATE"));
        dto.setDuree(rs.getInt("DUREE"));
        dto.setFormat(rs.getString("FORMAT"));
        dto.setQualite(rs.getInt("QUALITE"));
        dto.setResolution(rs.getString("RESOLUTION"));
        dto.setSerie(rs.getInt("SERIE"));
        dto.setSaison(rs.getInt("SAISON"));
        dto.setEpisode(rs.getInt("EPISODE"));
        dto.setTaille(rs.getInt("TAILLE"));
        return dto;
    }


}
