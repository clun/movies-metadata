package fr.clunven.docu.dao.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.dao.db.dto.GenreDto;

public class GenreRowMapper implements RowMapper<GenreDto>{

    @Override
    public GenreDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        GenreDto dto = new GenreDto();
        dto.setId(rs.getInt("ID"));
        dto.setName(rs.getString("NOM"));
        dto.setIcone(rs.getString("ICONE"));
        dto.setIdParent(rs.getInt("PARENT"));
        return dto;
    }

}
