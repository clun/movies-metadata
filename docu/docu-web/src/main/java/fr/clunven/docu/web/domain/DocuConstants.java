package fr.clunven.docu.web.domain;

/**
 * Constants to be used in the application.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public interface DocuConstants {
	
	// Manifest
	String MANIFEST_FILE		= "/META-INF/MANIFEST.MF";
	String MANIFEST_BUILDTIME	= "Build-Time";
	String MANIFEST_VERSION		= "Specification-Version";
	
	// View names
	String VIEW_HOME			= "home";
	String VIEW_DOCUMENTAIRE    = "documentaire";
	
	// User Pages
	String USERTAG_GENERAL 		= "general";
	
	// Bean names
	String BEAN_HOME            = "homebean";
    String BEAN_USER            = "userbean";
    String BEAN_MENU            = "menubean";
    String BEAN_LISTDOCU        = "doculist";
    
	// Session Attributes
	String PARAM_ERRORS = "errors";
	String PARAM_MSG    = "successMessages";
	
	// Role names
	String ROLE_USER    = "USER";
	String ROLE_ADMIN   = "ADMIN";
	
}
