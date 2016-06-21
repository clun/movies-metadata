package fr.clunven.docu.dao.db.dto;

/**
 * Table ref_format.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class FormatDto implements Comparable<FormatDto>{
    
    private String extension;
    
    private String nom;
    
    private String icone;

    /**
     * Getter accessor for attribute 'extension'.
     *
     * @return
     *       current value of 'extension'
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Setter accessor for attribute 'extension'.
     * @param extension
     * 		new value for 'extension '
     */
    public void setExtension(String extension) {
        this.extension = extension;
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

    /** {@inheritDoc} */
    public int compareTo(FormatDto o) {
        return this.extension.compareTo(o.getExtension());
    }


}
