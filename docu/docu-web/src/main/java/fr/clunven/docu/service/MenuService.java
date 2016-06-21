package fr.clunven.docu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.clunven.docu.dao.db.DocumentaryDbDao;
import fr.clunven.docu.dao.db.ReferentialDbDao;
import fr.clunven.docu.dao.db.dto.DocumentaireDetail;
import fr.clunven.docu.dao.db.dto.GenreDto;
import fr.clunven.docu.dao.db.dto.SubGenreDto;

@Component("service.menu")
public class MenuService {
    
    @Autowired
    private ReferentialDbDao refDbDao;
    
    @Autowired
    private DocumentaryDbDao docuDbDao;
    
    /**
     * Create Menu.
     * 
     * @return
     *      menu inforation
     */
    public Map < GenreDto, List < SubGenreDto > > getMenuDocumentairesByGenre() {
        return refDbDao.getMenu();
    }
    
    public List < DocumentaireDetail > getByGenre(int genre) {
        List < DocumentaireDetail > returns = docuDbDao.getByGenre(genre);
        /*
        for (DocumentaireDetail docu : returns) {
            if (docu.getDescription().length() > 200) {
                docu.setDescription(docu.getDescription().substring(0, 200) + " [...]");
            } else {
                // Right padding
                int missing = 200 - docu.getDescription().length();
                StringBuilder sb = new StringBuilder(docu.getDescription());
                for (int i= 0;i<missing;i++) {
                    sb.append("&nbsp;");
                }
                docu.setDescription(sb.toString());
            }
            if (docu.getTitre().length() < 35) {
                docu.setTitre(docu.getTitre() + "<br/>&nbsp;");
            }
        }*/
        return returns;
    }
    
    

}
