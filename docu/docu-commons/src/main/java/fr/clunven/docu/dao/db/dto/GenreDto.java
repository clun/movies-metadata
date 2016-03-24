package fr.clunven.docu.dao.db.dto;

import java.io.Serializable;

public class GenreDto implements Serializable, Comparable < GenreDto > {

    private String name;
    
    private int id;
    
    private String icone;
    
    private int idParent = -1;

    /**
     * Getter accessor for attribute 'name'.
     *
     * @return
     *       current value of 'name'
     */
    public String getName() {
        return name;
    }

    /**
     * Setter accessor for attribute 'name'.
     * @param name
     * 		new value for 'name '
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter accessor for attribute 'id'.
     *
     * @return
     *       current value of 'id'
     */
    public int getId() {
        return id;
    }

    /**
     * Setter accessor for attribute 'id'.
     * @param id
     * 		new value for 'id '
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter accessor for attribute 'icone'.
     *
     * @return
     *       current value of 'icone'
     */
    public String getIcone() {
        return icone;
    }

    /**
     * Setter accessor for attribute 'icone'.
     * @param icone
     * 		new value for 'icone '
     */
    public void setIcone(String icone) {
        this.icone = icone;
    }

    /**
     * Getter accessor for attribute 'idParent'.
     *
     * @return
     *       current value of 'idParent'
     */
    public int getIdParent() {
        return idParent;
    }

    /**
     * Setter accessor for attribute 'idParent'.
     * @param idParent
     * 		new value for 'idParent '
     */
    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    /** {@inheritDoc} */
    public int compareTo(GenreDto other) {
        return this.getName().compareTo(other.getName());
    }
    
}
