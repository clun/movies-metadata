package fr.clunven.docu.web.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import fr.clunven.docu.dao.db.dto.UserDto;

/**
 * Custom user to add profile, tetha, categories 
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class DocuUser extends User implements DocuConstants {
	
	/** serial. */
	private static final long serialVersionUID = 6164905892494619787L;
	
	/** custom property. */
	private String prenom = "";
	
	/** custom property. */
	private String nom = "";
	
	/** custom property. */
	private String image = "";
	
	/** custom property. */
	private boolean admin = false;
	
	/**
	 * Default connector.
	 * 
	 * @param userId
	 * @param userPassword
	 * @param isAdmin
	 */
	public DocuUser(UserDto dto, List<GrantedAuthority> permissions) {
	    super(dto.getUserName(), dto.getPassword(), true, true, true, true, permissions);
	    this.prenom = dto.getPrenom();
	    this.nom    = dto.getNom();
	    this.admin  = dto.isAdmin();
	    this.image  = dto.getImage();
	}
	
	/**
	 * Getter accessor for attribute 'image'.
	 *
	 * @return
	 *       current value of 'image'
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Setter accessor for attribute 'image'.
	 * @param image
	 * 		new value for 'image '
	 */
	public void setImage(String image) {
		this.image = image;
	}

    /**
     * Getter accessor for attribute 'admin'.
     *
     * @return
     *       current value of 'admin'
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Setter accessor for attribute 'admin'.
     * @param admin
     * 		new value for 'admin '
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    /**
     * Getter accessor for attribute 'prenom'.
     *
     * @return
     *       current value of 'prenom'
     */
    public String getPrenom() {
        return prenom;
    }


    /**
     * Setter accessor for attribute 'prenom'.
     * @param prenom
     * 		new value for 'prenom '
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    /**
     * Getter accessor for attribute 'nom'.
     *
     * @return
     *       current value of 'nom'
     */
    public String getNom() {
        return nom;
    }


    /**
     * Setter accessor for attribute 'nom'.
     * @param nom
     * 		new value for 'nom '
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

}
