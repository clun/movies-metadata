package fr.clunven.docu.web.controller;

import fr.clunven.docu.dao.db.dto.DocumentaireDetail;
import fr.clunven.docu.web.domain.DocuConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HomeController which load supervision services and display home page.
 */
@Controller("controller." + DocuConstants.VIEW_DOCUMENTAIRE_DISPLAY)
@RequestMapping("/" + DocuConstants.VIEW_DOCUMENTAIRE_DISPLAY + ".htm")
public class DocumentaireDisplayController extends BaseController {

    /**
     * Initialization of the Controller.
     */
    public DocumentaireDisplayController() {
        setSuccessView(VIEW_DOCUMENTAIRE_DISPLAY);
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
        String uid = request.getParameter("uid");

        if (uid == null || uid.isEmpty()) {
            throw new IllegalArgumentException("uid cannot be null in this controller");
        }

        ModelAndView mav = renderPage(request);
        // Permet d'afficher le détail d'un documentaire
        DocumentaireDetail dd = documentaryDbDao.getDocumentaireById(Integer.parseInt(uid));
        if (referentialDbDao.getPays(dd.getPays()) != null) {
            dd.setPaysIcone(referentialDbDao.getPays(dd.getPays()).getDrapeau());
        }
        if (referentialDbDao.getLangue(dd.getLangue()) != null) {
            dd.setLangueIcone(referentialDbDao.getLangue(dd.getLangue()).getIcone());
        }
        mav.addObject(BEAN_DOCUDETAIL, dd);

        // Affichage du menu de gauche
        mav.addObject(BEAN_MENU, menuService.getMenuDocumentairesByGenre());
        return mav;
    }

}
