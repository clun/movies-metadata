package fr.clunven.docu.dao.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.dao.db.dto.UserDto;

/**
 * User.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class UserRowMapper implements RowMapper<UserDto>{

    /** {@inheritDoc} */
    public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDto dto = new UserDto();
        dto.setUserName(rs.getString("userid"));
        dto.setNom(rs.getString("nom"));
        dto.setPrenom(rs.getString("prenom"));
        dto.setImage(rs.getString("image"));
        dto.setPassword(rs.getString("password"));
        dto.setAdmin(rs.getInt("isAdmin")>0);
        return dto;
    }

}
