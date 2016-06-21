package fr.clunven.docu.dao.db.dto;

public class DocumentaireDetail extends DocumentaireListElementDto {
    
    /** Serial. */
    private static final long serialVersionUID = -3540688279277773613L;

    private String titreOriginal;
    
    private String description;
    
    private String realisateur;
    
    private int duree;

    @Override
    public String toString() {
        return "DocumentaireDetail{" +
                "titreOriginal='" + titreOriginal + '\'' +
                ", description='" + description + '\'' +
                ", realisateur='" + realisateur + '\'' +
                ", duree=" + duree +
                ", image='" + image + '\'' +
                ", genreId=" + genreId +
                ", pays='" + pays + '\'' +
                ", paysIcone='" + paysIcone + '\'' +
                ", langue='" + langue + '\'' +
                ", langueIcone='" + langueIcone + '\'' +
                ", soustitre='" + soustitre + '\'' +
                ", note=" + note +
                ", taille=" + taille +
                ", format='" + format + '\'' +
                ", formatIcone='" + formatIcone + '\'' +
                ", bitrate=" + bitrate +
                ", qualite=" + qualite +
                ", resolution='" + resolution + '\'' +
                '}';
    }

    private String image;
    
    private int genreId;
    
    private String pays;
    
    private String paysIcone;
    
    private String langue;
    
    private String langueIcone;
    
    private String soustitre;
    
    private int note = 0;
    
    private int taille = 0;
    
    private String format;
    
    private String formatIcone;
    
    private int bitrate;
    
    private int qualite;
    
    private String resolution;

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
     * Getter accessor for attribute 'realisateur'.
     *
     * @return
     *       current value of 'realisateur'
     */
    public String getRealisateur() {
        return realisateur;
    }

    /**
     * Setter accessor for attribute 'realisateur'.
     * @param realisateur
     * 		new value for 'realisateur '
     */
    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    /**
     * Getter accessor for attribute 'duree'.
     *
     * @return
     *       current value of 'duree'
     */
    public int getDuree() {
        return duree;
    }

    /**
     * Setter accessor for attribute 'duree'.
     * @param duree
     * 		new value for 'duree '
     */
    public void setDuree(int duree) {
        this.duree = duree;
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

    /**
     * Getter accessor for attribute 'langue'.
     *
     * @return
     *       current value of 'langue'
     */
    public String getLangue() {
        return langue;
    }

    /**
     * Setter accessor for attribute 'langue'.
     * @param langue
     * 		new value for 'langue '
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * Getter accessor for attribute 'soustitre'.
     *
     * @return
     *       current value of 'soustitre'
     */
    public String getSoustitre() {
        return soustitre;
    }

    /**
     * Setter accessor for attribute 'soustitre'.
     * @param soustitre
     * 		new value for 'soustitre '
     */
    public void setSoustitre(String soustitre) {
        this.soustitre = soustitre;
    }

    /**
     * Getter accessor for attribute 'note'.
     *
     * @return
     *       current value of 'note'
     */
    public int getNote() {
        return note;
    }

    /**
     * Setter accessor for attribute 'note'.
     * @param note
     * 		new value for 'note '
     */
    public void setNote(int note) {
        this.note = note;
    }

   

    /**
     * Getter accessor for attribute 'taille'.
     *
     * @return
     *       current value of 'taille'
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Setter accessor for attribute 'taille'.
     * @param taille
     * 		new value for 'taille '
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Getter accessor for attribute 'format'.
     *
     * @return
     *       current value of 'format'
     */
    public String getFormat() {
        return format;
    }

    /**
     * Setter accessor for attribute 'format'.
     * @param format
     * 		new value for 'format '
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Getter accessor for attribute 'bitrate'.
     *
     * @return
     *       current value of 'bitrate'
     */
    public int getBitrate() {
        return bitrate;
    }

    /**
     * Setter accessor for attribute 'bitrate'.
     * @param bitrate
     * 		new value for 'bitrate '
     */
    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * Getter accessor for attribute 'qualite'.
     *
     * @return
     *       current value of 'qualite'
     */
    public int getQualite() {
        return qualite;
    }

    /**
     * Setter accessor for attribute 'qualite'.
     * @param qualite
     * 		new value for 'qualite '
     */
    public void setQualite(int qualite) {
        this.qualite = qualite;
    }

    /**
     * Getter accessor for attribute 'resolution'.
     *
     * @return
     *       current value of 'resolution'
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Setter accessor for attribute 'resolution'.
     * @param resolution
     * 		new value for 'resolution '
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * Getter accessor for attribute 'genreId'.
     *
     * @return
     *       current value of 'genreId'
     */
    public int getGenreId() {
        return genreId;
    }

    /**
     * Setter accessor for attribute 'genreId'.
     * @param genreId
     * 		new value for 'genreId '
     */
    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    /**
     * Getter accessor for attribute 'paysIcone'.
     *
     * @return
     *       current value of 'paysIcone'
     */
    public String getPaysIcone() {
        return paysIcone;
    }

    /**
     * Setter accessor for attribute 'paysIcone'.
     * @param paysIcone
     * 		new value for 'paysIcone '
     */
    public void setPaysIcone(String paysIcone) {
        this.paysIcone = paysIcone;
    }

    /**
     * Getter accessor for attribute 'langueIcone'.
     *
     * @return
     *       current value of 'langueIcone'
     */
    public String getLangueIcone() {
        return langueIcone;
    }

    /**
     * Setter accessor for attribute 'langueIcone'.
     * @param langueIcone
     * 		new value for 'langueIcone '
     */
    public void setLangueIcone(String langueIcone) {
        this.langueIcone = langueIcone;
    }

    /**
     * Getter accessor for attribute 'formatIcone'.
     *
     * @return
     *       current value of 'formatIcone'
     */
    public String getFormatIcone() {
        return formatIcone;
    }

    /**
     * Setter accessor for attribute 'formatIcone'.
     * @param formatIcone
     * 		new value for 'formatIcone '
     */
    public void setFormatIcone(String formatIcone) {
        this.formatIcone = formatIcone;
    }
        
       

}
