package fr.clunven.docu.dao.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.dao.db.dto.DocumentaireListElementDto;


/**
 * RowMapper for {@link DocumentaireListElementDto}.
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public class DocumentaireListElementRowMapper implements RowMapper< DocumentaireListElementDto>{
    
    /** {@inheritDoc} */
    public DocumentaireListElementDto mapRow(ResultSet rs, int rownNum) throws SQLException {
        DocumentaireListElementDto dto = new DocumentaireListElementDto();
        dto.setAnnee(rs.getInt("ANNEE"));;
        dto.setGenre(rs.getString("GENRE"));
        dto.setTitre(rs.getString("TITRE"));
        dto.setId(rs.getInt("ID"));
        dto.setVu(rs.getInt("VU") == 1);
        return dto;
    }

}
