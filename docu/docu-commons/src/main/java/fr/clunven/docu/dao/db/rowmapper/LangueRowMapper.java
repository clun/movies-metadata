package fr.clunven.docu.dao.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.dao.db.dto.LangueDto;

public class LangueRowMapper implements RowMapper< LangueDto > {

    /** {@inheritDoc} */
    public LangueDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        LangueDto dto = new LangueDto();
        dto.setCode(rs.getString("CODE"));
        dto.setNom(rs.getString("NOM"));
        dto.setIcone(rs.getString("DRAPEAU"));
        return dto;
    }

}
