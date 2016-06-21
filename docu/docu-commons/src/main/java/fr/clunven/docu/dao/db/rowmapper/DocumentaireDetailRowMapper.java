package fr.clunven.docu.dao.db.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.clunven.docu.dao.db.dto.DocumentaireDetail;

public class DocumentaireDetailRowMapper implements RowMapper < DocumentaireDetail >{

    /** {@inheritDoc} */
    public DocumentaireDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentaireDetail dd = new DocumentaireDetail();
        dd.setId(rs.getInt("ID"));
        dd.setTitre(rs.getString("TITRE"));
        dd.setTitreOriginal(rs.getString("TITRE_ORIGINAL"));
        dd.setDescription(rs.getString("DESCRIPTION"));
        dd.setRealisateur(rs.getString("REALISATEUR"));
        dd.setAnnee(rs.getInt("ANNEE"));
        dd.setDuree(rs.getInt("DUREE"));
        dd.setImage(rs.getString("IMAGE"));
        dd.setGenreId(rs.getInt("GENRE"));
        dd.setPays(rs.getString("PAYS"));
        dd.setLangue(rs.getString("LANGUE"));
        dd.setSoustitre(rs.getString("SOUSTITRES"));
        dd.setNote(rs.getInt("NOTE"));
        dd.setVu(rs.getInt("VU") == 1);
        dd.setTaille(rs.getInt("TAILLE"));
        dd.setFormat(rs.getString("FORMAT"));
        dd.setBitrate(rs.getInt("BITRATE"));
        dd.setQualite(rs.getInt("QUALITE"));
        dd.setResolution(rs.getString("RESOLUTION"));
        return dd;
    }

}
