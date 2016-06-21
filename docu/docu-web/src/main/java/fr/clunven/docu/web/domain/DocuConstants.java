package fr.clunven.docu.web.domain;

/**
 * Constants to be used in the application.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public interface DocuConstants {

	/* View names */
	String VIEW_HOME			     = "home";
	String VIEW_DOCUMENTAIRE         = "documentaire";
	String VIEW_DOCUMENTAIRE_GEN     = "documentaireByGenre";
	String VIEW_DOCUMENTAIRE_UPDATE  = "documentaireUpdate";
	String VIEW_DOCUMENTAIRE_DISPLAY = "documentaireDisplay";


	String BEAN_USER            = "userbean";
    String BEAN_MENU            = "menubean";
    String BEAN_REF_FORMAT      = "refFormat";
    String BEAN_REF_LANGUE      = "refLangue";
    String BEAN_REF_PAYS        = "refPays";
    
    String BEAN_LISTDOCU        = "doculist";
    String BEAN_DOCUBYGENRE     = "listdocubean";
    String BEAN_GENRE           = "genre";
    String BEAN_DOCUDETAIL		= "docuDetail";


	// Session Attributes
	String PARAM_ERRORS = "errors";
	String PARAM_MSG    = "successMessages";
	
	// Role names
	String ROLE_USER    = "USER";
	String ROLE_ADMIN   = "ADMIN";

}
