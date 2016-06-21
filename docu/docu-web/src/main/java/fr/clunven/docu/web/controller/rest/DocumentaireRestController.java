package fr.clunven.docu.web.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.clunven.docu.dao.db.dto.DocumentaireDetail;
import fr.clunven.docu.web.controller.BaseController;

/**
 * HomeController which load supervision services and display home page.
 */
@Controller("controller.api")
@RequestMapping("/api/docu")
public class DocumentaireRestController extends BaseController {
	
	/**
	 * Initialization of the Controller.
	 */
	public DocumentaireRestController() {
		setSuccessView(VIEW_DOCUMENTAIRE);
		setCancelView(null);
	}
	
	@RequestMapping(value="/{uid}", method = RequestMethod.GET)
	public ModelAndView Render(Object model, HttpServletResponse response, @PathVariable String uid) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;
        try {
            DocumentaireDetail dd = documentaryDbDao.getDocumentaireById(new Integer(uid));
            // Overriding
            if (null != referentialDbDao.getPays(dd.getPays())) {
                dd.setPaysIcone(referentialDbDao.getPays(dd.getPays()).getDrapeau());
            }
            if (null != referentialDbDao.getLangue(dd.getLangue())) {
                dd.setLangueIcone(referentialDbDao.getLangue(dd.getLangue()).getIcone());
            }
            if (null != referentialDbDao.getFormat(dd.getFormat())) {
                dd.setFormatIcone(referentialDbDao.getFormat(dd.getFormat()).getIcone());
            }
            jsonConverter.write(dd, jsonMimeType, new ServletServerHttpResponse(response));
        } catch (Exception e) {
           throw new IllegalArgumentException("Error during documentary detail", e);
        }
        return null;
    }
	
}
