package fr.clunven.docu.dao.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.dao.db.dto.SerieDto;

public class SerieRowMapper implements RowMapper < SerieDto > {

    /** {@inheritDoc} */
    public SerieDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        SerieDto dto = new SerieDto();
        dto.setId(rs.getInt("ID"));
        dto.setTitre(rs.getString("TITRE"));
        dto.setTitreOriginal(rs.getString("TITRE_ORIGINAL"));
        dto.setDescription(rs.getString("DESCRIPTION"));
        dto.setImage(rs.getString("IMAGE"));
        dto.setAnneeDebut(rs.getInt("ANNEE_DEBUT"));
        dto.setAnneeFin(rs.getInt("ANNEE_FIN"));
        dto.setGenre(rs.getInt("GENRE"));
        dto.setPays(rs.getString("PAYS"));
        return dto;
    }

}
