package fr.clunven.docu.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.clunven.docu.web.domain.DocuConstants;

/**
 * HomeController which load supervision services and display home page.
 */
@Controller("controller." + DocuConstants.VIEW_DOCUMENTAIRE)
@RequestMapping("/" + DocuConstants.VIEW_DOCUMENTAIRE + ".htm")
public class DocumentaireController extends BaseController {
	
	/**
	 * Initialization of the Controller.
	 */
	public DocumentaireController() {
		setSuccessView(VIEW_DOCUMENTAIRE);
		setCancelView(null);
	}
	
    /**
     * Allows to display screen.
     * 
     * @param request
     *          http request
     * @param response
     *          http response
     * @return
     *          model and view
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// Recherche de la liste en mode tableau de tous les documentaires
        ModelAndView mav = renderPage(request);
        mav.addObject(BEAN_LISTDOCU, documentaryDbDao.list());
        return mav;
    }

}
