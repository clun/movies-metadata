package fr.clunven.docu.dao.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.clunven.docu.dao.db.dto.UserDto;
import fr.clunven.docu.dao.db.rowmapper.UserRowMapper;

/**
 * Service for login.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Repository("docu.dao.db.user")
public class UserDbDao extends AbstractDaoSupport {
    
    /** User row mapper. */
    private static final UserRowMapper ROWMAPPER = new UserRowMapper();
    
    /** user. */
    private static final String QUERY_EXIST = "SELECT COUNT(*) FROM t_user WHERE userid LIKE ?";
    
    /** user. */
    private static final String QUERY_BYID = "SELECT * FROM t_user WHERE userid LIKE ?";
    
    /**
     * Check user existence.
     */
    public boolean exist(String userName) {
        return getJdbcTemplate().queryForObject(QUERY_EXIST, Integer.class, userName) > 0;
    }
    
    /**
     * Load user.
     */
    public UserDto loadUserByUid(String userName) {
        return getJdbcTemplate().queryForObject(QUERY_BYID, ROWMAPPER, userName);
    }
    

}
