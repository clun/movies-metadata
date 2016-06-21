package fr.clunven.docu.dao.db.dto;

public class LangueDto implements Comparable< LangueDto >{
    
    private String code;
    
    private String nom;
    
    private String icone;

    /**
     * Getter accessor for attribute 'code'.
     *
     * @return
     *       current value of 'code'
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter accessor for attribute 'code'.
     * @param code
     * 		new value for 'code '
     */
    public void setCode(String code) {
        this.code = code;
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
    public int compareTo(LangueDto o) {
        return this.nom.compareTo(o.getNom());
    }
    
}
