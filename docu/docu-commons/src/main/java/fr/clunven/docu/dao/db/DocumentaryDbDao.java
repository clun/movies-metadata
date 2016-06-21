package fr.clunven.docu.dao.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.clunven.docu.dao.db.dto.DocumentaireDetail;
import fr.clunven.docu.dao.db.dto.DocumentaireListElementDto;
import fr.clunven.docu.dao.db.rowmapper.DocumentaireDetailRowMapper;
import fr.clunven.docu.dao.db.rowmapper.DocumentaireListElementRowMapper;
import fr.clunven.docu.domain.Documentaire;

/**
 * Mise à jour de la table documentaires dans la base
 */
@Repository("docu.dao.db.documentary")
public class DocumentaryDbDao extends AbstractDaoSupport {
   
    /** logger for this class. */
    private Logger logger = LoggerFactory.getLogger(DocumentaryDbDao.class);
    
    /** Query. */
    private static final String QUERY_EXIST =
            "SELECT COUNT(*) FROM t_documentaire WHERE UPPER(TITRE) LIKE ?";
    
    /** Query. */
    private static final String QUERY_GETBY_ID =
            "SELECT ID FROM t_documentaire WHERE UPPER(TITRE) LIKE ?";

    /** list documentary. */
    private static final String QUERY_GETDOCUMENTARYLIST =
            "SELECT D.ID, D.TITRE, D.ANNEE, D.VU, G.nom as GENRE "
           + "FROM t_documentaire D, ref_genre G "
           + "WHERE D.GENRE = G.ID "
           + "ORDER BY D.TITRE ASC";
    
    /** Query. */
    private static final String QUERY_LISTGENRE = 
            "SELECT * FROM t_documentaire "
          + "WHERE genre = ? ORDER BY TITRE ASC";
    
    /** Query. */
    private static final String QUERY_GETBYID = 
            "SELECT * FROM t_documentaire WHERE ID = ? ";
    
    /** Query.
    private static final String QUERY_CREATE = 
            "INSERT INTO t_documentaire (ID, TITRE, TITRE_ORIGINAL, DESCRIPTION, "
           + "REALISATEUR, ANNEE, DUREE, IMAGE, GENRE, PAYS, LANGUE, SOUSTITRES, NOTE, VU, TAILLE, FORMAT, "
           + "BITRATE, QUALITE, RESOLUTION) VALUES (NULL, ?, '', '', '', ?, ?, '', ?, NULL, 'FR', NULL, "
           + "NULL, '0', ?, ?, ?, '0', ?)";
     */
    
    /** Query. */
    private static final String UPDATE_QUERY = 
            "UPDATE t_documentaire SET ANNEE=?,DUREE=?,TAILLE=?,FORMAT=?,BITRATE=?,"
          + "RESOLUTION=? WHERE ID = ?";

    private static final String UPDATE_QUERY_FULL =
            "UPDATE t_documentaire SET TITRE=?, TITRE_ORIGINAL=?, DESCRIPTION=?, ANNEE=?, DUREE=?, "
            + "IMAGE=?, PAYS=?, LANGUE=?, SOUSTITRES=?, NOTE=?, VU=?, TAILLE=?, FORMAT=?, QUALITE=?, RESOLUTION=? "
            + "WHERE ID = ?";

    /** RowMapper. */
    private static final DocumentaireListElementRowMapper DOCU_ROW_MAPPER = new DocumentaireListElementRowMapper();
            
    /** RowMapper. */
    private static final DocumentaireDetailRowMapper FULL_ROWMAPPER = new DocumentaireDetailRowMapper();
            
    /**
     * Tester l'existence d'une entrée dans la table t_Documentaire.
     */
    public boolean exist(String titre) {
        return getJdbcTemplate().queryForObject(QUERY_EXIST, Integer.class, titre.toUpperCase().trim()) > 0;
    }   
    
    public List < DocumentaireListElementDto > list() {
        return getJdbcTemplate().query(QUERY_GETDOCUMENTARYLIST, DOCU_ROW_MAPPER);
    }
    
    public List < DocumentaireDetail > getByGenre(int genre) {
        return getJdbcTemplate().query(QUERY_LISTGENRE, FULL_ROWMAPPER, genre);
    }
    
    public DocumentaireDetail getDocumentaireById(int uid) {
        return getJdbcTemplate().queryForObject(QUERY_GETBYID, FULL_ROWMAPPER, uid);
    }
    
    /**
     * Teste que ca existe et que la resolution est nulle (batch)
     *
    private boolean isResolutionNull(String titre) {
        return getJdbcTemplate().queryForObject(QUERY_EXIST + " AND " + CLAUSE_RESOLUTION_NULL, 
                Integer.class, titre.toUpperCase().trim()) > 0;
    }*/
    
    /**
     * Recherche de l'identifiant unique depuis le titre.
     */
    private int getIdFromTitle(String titre) {
        if (!exist(titre)) {
            throw new IllegalArgumentException("Cannot find DOCUMENTAIRE : ");
        }
        return getJdbcTemplate().queryForObject(QUERY_GETBY_ID, Integer.class, titre.toUpperCase().trim());
    }
  
    /**
     * Creer si non existant, sinon met à jour.
     *
    public void upsert(Documentaire ep) {
        if (!exist(ep.getTitre())) {
            create(ep);
        } else if (isResolutionNull(ep.getTitre())) {
            update(ep);
        } else {
            logger.debug("[OK] " +  ep.getTitre());
        }
    }*/

    public void update(DocumentaireDetail docu) {
        // La première chose est de reprendre la partie ancienne et de surcharger si nécessaires
        DocumentaireDetail before = getDocumentaireById(docu.getId());
        if (docu.getImage() == null) {
            docu.setImage(before.getImage());
        }
        getJdbcTemplate().update(UPDATE_QUERY_FULL,
                docu.getTitre(),
                docu.getTitreOriginal(), docu.getDescription(),
                docu.getAnnee(), docu.getDuree(),
                docu.getImage(), docu.getPays(), docu.getLangue(),
                docu.getSoustitre(), docu.getNote(),
                docu.isVu() ? 1 : 0, docu.getTaille(), docu.getFormat(),
                docu.getQualite(), docu.getResolution(),
                docu.getId());
    }

    /*
    private void create(Documentaire ep) {
        getJdbcTemplate().update(QUERY_CREATE, ep.getTitre(), 
                ep.getAnnee(), ep.getDuree() / 1000 / 60, 
                ep.getGenre(),
                ep.getTaille() /1024 / 1024,
                ep.getFormatCode(), 
                ep.getBitrate(), 
                ep.getResolution());
       
        logger.info("[CREATION] " + ep.getTitre()
        + "' : annee=" + ep.getAnnee() + " duree=" + ep.getDuree() + 
        " format=" + ep.getFormat() +
        " resolution=" + ep.getResolution() + " bitrate=" + ep.getBitrate());
    }*/
    
    public void update(Documentaire ep) {
        getJdbcTemplate().update(UPDATE_QUERY,
                ep.getAnnee(), ep.getDuree() / 1000 / 60,  
                ep.getTaille() /1024 / 1024,
                ep.getFormatCode(), 
                ep.getBitrate(), 
                ep.getResolution(),
                getIdFromTitle(ep.getTitre()));
        
        logger.info("[UPDATE] " + ep.getTitre()
        + "' : annee=" + ep.getAnnee() + " duree=" + ep.getDuree() + 
        " format=" + ep.getFormat() +
        " resolution=" + ep.getResolution() + " bitrate=" + ep.getBitrate());
    }
    
}
