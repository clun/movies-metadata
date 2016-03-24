package fr.clunven.docu.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.clunven.docu.dao.db.UserDbDao;
import fr.clunven.docu.dao.db.dto.UserDto;
import fr.clunven.docu.web.domain.DocuConstants;
import fr.clunven.docu.web.domain.DocuUser;

/**
 * Authentication services based on dedicated configuration file.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
@Component("auth.loginservices")
public class AuthenticationService implements UserDetailsService, DocuConstants {

    /** logger. */
    protected Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UserDbDao userDbDao;

    /** {@inheritDoc} */
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        log.info("Try to Authenticate User <" + userid + ">");
        if (!userDbDao.exist(userid)) {
        	log.info("User <" + userid + "> does not exist");
        	throw new UsernameNotFoundException("Cannot find user " + userid);
        }
        UserDto dto = userDbDao.loadUserByUid(userid);
        log.info("User Loaded {}", dto);
        List < GrantedAuthority > permissions = new ArrayList<>();
        permissions.add(new SimpleGrantedAuthority("ROLE_" + ROLE_USER));
        if (dto.isAdmin()) {
            permissions.add(new SimpleGrantedAuthority("ROLE_" + ROLE_ADMIN));
        }
        return new DocuUser(dto, permissions);
    }

}