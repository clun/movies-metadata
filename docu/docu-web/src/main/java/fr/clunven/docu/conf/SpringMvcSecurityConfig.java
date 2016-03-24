package fr.clunven.docu.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import fr.clunven.docu.service.AuthenticationService;
import fr.clunven.docu.web.domain.DocuConstants;

/**
 * Configuration Spring Security.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Configuration
@EnableWebSecurity
public class SpringMvcSecurityConfig extends WebSecurityConfigurerAdapter implements DocuConstants {
	
	@Autowired
	private AuthenticationService authServicer;
	
	/** {@inheritDoc} */
	protected void configure(HttpSecurity http) throws Exception {
		// login Page
		http.
		
		// LOGIN
		formLogin().
			loginPage("/login.htm").
			defaultSuccessUrl("/home.htm").			
			loginProcessingUrl("/j_security_check.htm").
			usernameParameter("j_username").
			passwordParameter("j_password").
			failureUrl("/login.htm?error=true").
		
		// LOGOUT
		and().
		    logout().
		    logoutUrl("/logout.htm").
		    logoutSuccessUrl("/login.htm").//
		
		// Http BASIC
		and().httpBasic().//
		
		// DISABLE
		and().csrf().disable(). //
			
		authorizeRequests(). //
			antMatchers("/img/**").permitAll().
			antMatchers("/css/**").permitAll().
			antMatchers("/js/**").permitAll().
			antMatchers("/fonts/**").permitAll().
			antMatchers("/login.htm").permitAll().
			
			antMatchers("/home.htm").hasRole(ROLE_USER).
			antMatchers("/**").hasRole(ROLE_USER).
			
		anyRequest().authenticated();
	}
	
	@Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setRequestContextAttribute("rc");
        return resolver;
    }
	
	/**
	 * Configuration for web security.
	 *
	 * @param auth
	 * 		authentication
	 * @throws Exception
	 * 		current exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	throws Exception {
		//auth.authenticationProvider(authProvider);
		auth.userDetailsService(authServicer);
	}

}
