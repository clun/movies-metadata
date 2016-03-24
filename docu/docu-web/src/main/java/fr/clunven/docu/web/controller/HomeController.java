package fr.clunven.docu.web.controller;

import javax.annotation.PostConstruct;
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
@Controller("controller." + DocuConstants.VIEW_HOME)
@RequestMapping("/" + DocuConstants.VIEW_HOME + ".htm")
public class HomeController extends BaseController {
	
    /**
	 * Initialization of the Controller.
	 */
	public HomeController() {
		setSuccessView(VIEW_HOME);
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
    	
    	// Get Information for
    	String action = request.getParameter("action");
        String id     = request.getParameter("id");
        //log.info("Event " + id + "  has been mask as read");
       
        ModelAndView mav = renderPage(request);
        
        return mav;
    }

}
