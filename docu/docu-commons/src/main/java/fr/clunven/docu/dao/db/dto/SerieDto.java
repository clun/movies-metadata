package fr.clunven.docu.dao.db.dto;

public class SerieDto {
    
    private int id;
    
    private String titre;
    
    private String titreOriginal;
    
    private String description;
    
    private String image;
    
    private int anneeDebut;
    
    private int anneeFin;
    
    private int genre;
    
    private String pays;
    
    public SerieDto() {
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
     * Getter accessor for attribute 'titreOriginal'.
     *
     * @return
     *       current value of 'titreOriginal'
     */
    public String getTitreOriginal() {
        return titreOriginal;
    }

    /**
     * Setter accessor for attribute 'titreOriginal'.
     * @param titreOriginal
     * 		new value for 'titreOriginal '
     */
    public void setTitreOriginal(String titreOriginal) {
        this.titreOriginal = titreOriginal;
    }

    /**
     * Getter accessor for attribute 'description'.
     *
     * @return
     *       current value of 'description'
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter accessor for attribute 'description'.
     * @param description
     * 		new value for 'description '
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Getter accessor for attribute 'anneeDebut'.
     *
     * @return
     *       current value of 'anneeDebut'
     */
    public int getAnneeDebut() {
        return anneeDebut;
    }

    /**
     * Setter accessor for attribute 'anneeDebut'.
     * @param anneeDebut
     * 		new value for 'anneeDebut '
     */
    public void setAnneeDebut(int anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    /**
     * Getter accessor for attribute 'anneeFin'.
     *
     * @return
     *       current value of 'anneeFin'
     */
    public int getAnneeFin() {
        return anneeFin;
    }

    /**
     * Setter accessor for attribute 'anneeFin'.
     * @param anneeFin
     * 		new value for 'anneeFin '
     */
    public void setAnneeFin(int anneeFin) {
        this.anneeFin = anneeFin;
    }

    /**
     * Getter accessor for attribute 'genre'.
     *
     * @return
     *       current value of 'genre'
     */
    public int getGenre() {
        return genre;
    }

    /**
     * Setter accessor for attribute 'genre'.
     * @param genre
     * 		new value for 'genre '
     */
    public void setGenre(int genre) {
        this.genre = genre;
    }

    /**
     * Getter accessor for attribute 'pays'.
     *
     * @return
     *       current value of 'pays'
     */
    public String getPays() {
        return pays;
    }

    /**
     * Setter accessor for attribute 'pays'.
     * @param pays
     * 		new value for 'pays '
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "SerieDto [id=" + id + ", titre=" + titre + ", genre=" + genre + "]";
    }

}
