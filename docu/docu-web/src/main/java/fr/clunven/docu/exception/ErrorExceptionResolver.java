package fr.clunven.docu.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * Handle Exceptions.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
@Component
public class ErrorExceptionResolver extends SimpleMappingExceptionResolver {
    
	/**
	 * An error occured
	 */
	public ErrorExceptionResolver() {
        setWarnLogCategory(ErrorExceptionResolver.class.getName());
    }

    /** {@inheritDoc} */
    @Override
    public String buildLogMessage(Exception e, HttpServletRequest req) {
    	System.out.println("buildlog mesage");
        return "MVC exception: " + e.getLocalizedMessage();
    }
    
    /** {@inheritDoc} */
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception exception) {
        // Call super method to get the ModelAndView
    	System.out.println("resolve exception");
        ModelAndView mav = super.doResolveException(request, response, handler, exception);
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
}