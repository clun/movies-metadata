package fr.clunven.docu.dao.db.dto;

public class DocumentaireListElementDto {
    
    private int uid;
    
    private String titre;
    
    private int annee;
    
    private String genre;
    
    private boolean vu = false;

    /**
     * Getter accessor for attribute 'uid'.
     *
     * @return
     *       current value of 'uid'
     */
    public int getUid() {
        return uid;
    }

    /**
     * Setter accessor for attribute 'uid'.
     * @param uid
     * 		new value for 'uid '
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Getter accessor for attribute 'titre'.
     *
     * @return
     *       current value of 'titre'
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Setter accessor for attribute 'titre'.
     * @param titre
     * 		new value for 'titre '
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Getter accessor for attribute 'annee'.
     *
     * @return
     *       current value of 'annee'
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Setter accessor for attribute 'annee'.
     * @param annee
     * 		new value for 'annee '
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Getter accessor for attribute 'genre'.
     *
     * @return
     *       current value of 'genre'
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter accessor for attribute 'genre'.
     * @param genre
     * 		new value for 'genre '
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Getter accessor for attribute 'vu'.
     *
     * @return
     *       current value of 'vu'
     */
    public boolean isVu() {
        return vu;
    }

    /**
     * Setter accessor for attribute 'vu'.
     * @param vu
     * 		new value for 'vu '
     */
    public void setVu(boolean vu) {
        this.vu = vu;
    }

}
