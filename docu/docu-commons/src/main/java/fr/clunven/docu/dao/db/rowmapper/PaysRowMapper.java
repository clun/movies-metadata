package fr.clunven.docu.dao.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.dao.db.dto.PaysDto;

public class PaysRowMapper implements RowMapper < PaysDto > {

    /** {@inheritDoc} */
    public PaysDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaysDto dto = new PaysDto();
        dto.setDrapeau(rs.getString("DRAPEAU"));
        dto.setNom(rs.getString("NOM"));
        return dto;
    }
    

}
