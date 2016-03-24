package fr.clunven.docu.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * HomeController which load supervision services and display home page.
 */
@ControllerAdvice
public class ErrorHandlerController {
	
	/** Logger. */
    protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised ", ex);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exClass", ex.getClass());
		mav.addObject("exMessage", ex.getMessage());
		mav.addObject("exStack", ex.getStackTrace());
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}
	

}
