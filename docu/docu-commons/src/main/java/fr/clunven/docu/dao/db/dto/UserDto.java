package fr.clunven.docu.dao.db.dto;

import java.io.Serializable;

/**
 * User DTO.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class UserDto implements Serializable {
    
    /** Serial. */
    private static final long serialVersionUID = 6204010124249712122L;

    private String userName;
    
    private String password;
    
    private String image;
    
    private boolean isAdmin = false;
    
    private String prenom;
    
    private String nom;

    /**
     * User DTO.
     */
    public UserDto() {
    }
    
    /**
     * Getter accessor for attribute 'userName'.
     *
     * @return
     *       current value of 'userName'
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter accessor for attribute 'userName'.
     * @param userName
     * 		new value for 'userName '
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter accessor for attribute 'password'.
     *
     * @return
     *       current value of 'password'
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter accessor for attribute 'password'.
     * @param password
     * 		new value for 'password '
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Getter accessor for attribute 'isAdmin'.
     *
     * @return
     *       current value of 'isAdmin'
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Setter accessor for attribute 'isAdmin'.
     * @param isAdmin
     * 		new value for 'isAdmin '
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "UserDto [userName=" + userName + ", password=" + password + ", image=" + image + ", isAdmin=" + isAdmin
                + ", prenom=" + prenom + ", nom=" + nom + "]";
    }
    

}
