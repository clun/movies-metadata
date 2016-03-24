package fr.clunven.docu.dao.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.dao.db.dto.SubGenreDto;

public class SubGenreRowMapper implements RowMapper<SubGenreDto>{

    private static final GenreRowMapper GENRE_ROWMAPPER = new GenreRowMapper();
    
    @Override
    public SubGenreDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubGenreDto dto = new SubGenreDto();
        dto.setSubgenre(GENRE_ROWMAPPER.mapRow(rs, rowNum));
        dto.setNb(rs.getInt("NB"));
        return dto;
    }

}
