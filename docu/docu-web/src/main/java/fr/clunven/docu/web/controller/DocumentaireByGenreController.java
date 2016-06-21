package fr.clunven.docu.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.clunven.docu.web.domain.DocuConstants;

@Controller("controller." + DocuConstants.VIEW_DOCUMENTAIRE_GEN)
@RequestMapping("/" + DocuConstants.VIEW_DOCUMENTAIRE_GEN + ".htm")
public class DocumentaireByGenreController extends BaseController {
    
    public DocumentaireByGenreController() {
        setSuccessView(VIEW_DOCUMENTAIRE_GEN);
        setCancelView(null);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String genre = request.getParameter("genre");
        
        // Recherche de la liste en mode tableau de tous les documentaires
        ModelAndView mav = renderPage(request);
        mav.addObject(BEAN_MENU, menuService.getMenuDocumentairesByGenre());
        
        // Recherche de documentaire pour ce genre
        if (genre != null) {
            int genreInt = new Integer(genre);
            mav.addObject(BEAN_GENRE, referentialDbDao.getGenreById(genreInt));
            mav.addObject(BEAN_DOCUBYGENRE, menuService.getByGenre(genreInt));
        }
      
        return mav;
    }

}
