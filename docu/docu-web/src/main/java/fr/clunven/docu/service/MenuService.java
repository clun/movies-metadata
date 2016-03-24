package fr.clunven.docu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.clunven.docu.dao.db.ReferentialDbDao;
import fr.clunven.docu.dao.db.dto.GenreDto;
import fr.clunven.docu.dao.db.dto.SubGenreDto;

@Component("service.menu")
public class MenuService {
    
    @Autowired
    private ReferentialDbDao refDbDao;
    
    /**
     * Create Menu.
     * 
     * @return
     *      menu inforation
     */
    public Map < GenreDto, List < SubGenreDto > > getMenu() {
        return refDbDao.getMenu();
    }

}
