package fr.clunven.docu.dao.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.dao.db.dto.FormatDto;

public class FormatRowMapper implements RowMapper < FormatDto > {

    /** {@inheritDoc} */
    public FormatDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        FormatDto dto = new FormatDto();
        dto.setExtension(rs.getString("EXTENSION"));
        dto.setNom(rs.getString("NOM"));
        dto.setIcone(rs.getString("ICONE"));
        return dto;
    }

}
