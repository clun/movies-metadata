package fr.clunven.docu.dao.db.dto;

public class PaysDto implements Comparable< PaysDto >{
    
    private String nom;
    
    private String drapeau;

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
     * Getter accessor for attribute 'drapeau'.
     *
     * @return
     *       current value of 'drapeau'
     */
    public String getDrapeau() {
        return drapeau;
    }

    /**
     * Setter accessor for attribute 'drapeau'.
     * @param drapeau
     * 		new value for 'drapeau '
     */
    public void setDrapeau(String drapeau) {
        this.drapeau = drapeau;
    }

    /** {@inheritDoc} */
    public int compareTo(PaysDto o) {
        return this.nom.compareTo(o.getNom());
    }

}
