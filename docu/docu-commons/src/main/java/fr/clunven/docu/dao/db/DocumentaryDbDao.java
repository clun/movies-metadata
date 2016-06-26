package fr.clunven.docu.dao.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.SingleColumnRowMapper;
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
    
    /** Query. */
    private static final String QUERY_EXIST =
            "SELECT COUNT(*) FROM t_documentaire WHERE UPPER(TITRE) LIKE ? AND GENRE = ?";
 
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
    private static final String QUERY_LISTGENRE_TITRE = 
            "SELECT TITRE FROM t_documentaire "
          + "WHERE genre = ? ORDER BY TITRE ASC";
    
    
    /** Query. */
    private static final String QUERY_GETBYID = 
            "SELECT * FROM t_documentaire WHERE ID = ? ";
    
    /** Query. */
    private static final String QUERY_CREATE = 
            "INSERT INTO t_documentaire (ID, TITRE, TITRE_ORIGINAL, DESCRIPTION, "
           + "REALISATEUR, ANNEE, DUREE, IMAGE, GENRE, PAYS, LANGUE, SOUSTITRES, NOTE, VU, TAILLE, FORMAT, "
           + "BITRATE, QUALITE, RESOLUTION) VALUES (NULL, ?, '', '', '', ?, ?, '', ?, NULL, 'FR', NULL, "
           + "NULL, '0', ?, ?, ?, ?, ?)";
    
    /** Query. */
    private static final String UPDATE_QUERY = 
            "UPDATE t_documentaire SET TITRE=?, ANNEE=?, DUREE=?, TAILLE=?, FORMAT=?, BITRATE=?, RESOLUTION=?, QUALITE= ? WHERE ID = ?";

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
    public boolean exist(String titre, int genre) {
        return getJdbcTemplate().queryForObject(QUERY_EXIST, Integer.class, titre.toUpperCase().trim(), genre) > 0;
    }   
    
    public List < DocumentaireListElementDto > list() {
        return getJdbcTemplate().query(QUERY_GETDOCUMENTARYLIST, DOCU_ROW_MAPPER);
    }
    
    public List < DocumentaireDetail > getByGenre(int genre) {
        return getJdbcTemplate().query(QUERY_LISTGENRE, FULL_ROWMAPPER, genre);
    }
    
    public Set < String > getDocumentaireNamesByGenre(int genre) {
        return new TreeSet<String>(
                getJdbcTemplate().query(QUERY_LISTGENRE_TITRE, 
                        new SingleColumnRowMapper<String>(), genre));
    }
    
    public Map < String, Integer > getDocumentaireNamesMapByGenre(int genre) {
        Map < String, Integer > documentairesMap = new HashMap<>();
        for (DocumentaireDetail dd : getByGenre(genre)) {
            documentairesMap.put(dd.getTitre(), dd.getId());
        }
        return documentairesMap;
    }
    
    public DocumentaireDetail getDocumentaireById(int uid) {
        return getJdbcTemplate().queryForObject(QUERY_GETBYID, FULL_ROWMAPPER, uid);
    }
    
    /**
     * Create new documentaire from bean.
     *
     * @param ep
     */
    public void create(Documentaire ep) {
        getJdbcTemplate().update(QUERY_CREATE, 
                ep.getTitre(), 
                ep.getAnnee(), ep.getDuree() / 1000 / 60, 
                ep.getGenre(),
                ep.getTaille() /1024 / 1024,
                ep.getFormatCode(), 
                ep.getBitrate(), 
                ep.getQualite(),
                ep.getResolution());
    }
    
    public void update(DocumentaireDetail docu) {
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
    
    public void updateMetaData(int uid, Documentaire ep) {
        getJdbcTemplate().update(UPDATE_QUERY,
                ep.getTitre(),
                ep.getAnnee(), 
                ep.getDuree() / 1000 / 60,  
                ep.getTaille() / 1024 / 1024,
                ep.getFormatCode(), 
                ep.getBitrate(),
                ep.getResolution(),
                ep.getQualite(),uid);
    }
    
}
