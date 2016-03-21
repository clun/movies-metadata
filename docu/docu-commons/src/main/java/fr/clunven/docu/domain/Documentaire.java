package fr.clunven.docu.domain;

import fr.clunven.docu.media.General;
import fr.clunven.docu.media.MovieMetadata;
import fr.clunven.docu.media.Video;

/**
 * Simple Episode.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class Documentaire {
    
    protected String titre;
    
    protected int genre;
   
    protected int annee; 
    
    protected long duree;
    
    protected long taille;
    
    protected String format;
    
    protected int qualite;
    
    protected long bitrate;
    
    protected String resolution;
    
    protected boolean vo = false;
    
    protected String extension = "";
    
    public Documentaire() {
    }
    
    /**
     * Get format code by its name.
     * @return
     *      list of code
     */
    public String getFormatCode() {
        if (format == null) return "MP4";
        switch(format) {
            case "Windows Media":  return "WMV";
            case "Flash Video":    return "FLV";
            case "MPEG-4":         return "MP4";
            case "Matroska":       return "MKV";
            case "AVI":            return "AVI";
            default:               return "MP4";
        }
    }
    
    public Documentaire(MovieMetadata mmd) {
        if (mmd.get(General.DURATION).isPresent()) {
            duree = Long.parseLong(mmd.get(General.DURATION).get());
        }
        if (mmd.get(General.FORMAT).isPresent()) {
            format = mmd.get(General.FORMAT).get();
        }
        if (mmd.get(General.FILESIZE).isPresent()) {
            taille = Long.parseLong( mmd.get(General.FILESIZE).get());
        }
        if (mmd.get(Video.WIDTH).isPresent() && mmd.get(Video.HEIGHT).isPresent()) {
           resolution = mmd.get(Video.WIDTH).get() + "x" + mmd.get(Video.HEIGHT).get();
        }
        if (mmd.get(Video.BITRATE).isPresent()) {
            bitrate = Long.parseLong(mmd.get(Video.BITRATE).get());
        }
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
     * Setter accessor for attribute 'bitrate'.
     * @param bitrate
     * 		new value for 'bitrate '
     */
    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
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
     * Getter accessor for attribute 'vo'.
     *
     * @return
     *       current value of 'vo'
     */
    public boolean isVo() {
        return vo;
    }

    /**
     * Setter accessor for attribute 'vo'.
     * @param vo
     * 		new value for 'vo '
     */
    public void setVo(boolean vo) {
        this.vo = vo;
    }

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
     * Getter accessor for attribute 'duree'.
     *
     * @return
     *       current value of 'duree'
     */
    public long getDuree() {
        return duree;
    }

    /**
     * Getter accessor for attribute 'taille'.
     *
     * @return
     *       current value of 'taille'
     */
    public long getTaille() {
        return taille;
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
     * Getter accessor for attribute 'qualite'.
     *
     * @return
     *       current value of 'qualite'
     */
    public int getQualite() {
        return qualite;
    }

    /**
     * Getter accessor for attribute 'bitrate'.
     *
     * @return
     *       current value of 'bitrate'
     */
    public long getBitrate() {
        return bitrate;
    }

    /**
     * Setter accessor for attribute 'duree'.
     * @param duree
     * 		new value for 'duree '
     */
    public void setDuree(long duree) {
        this.duree = duree;
    }

    /**
     * Setter accessor for attribute 'taille'.
     * @param taille
     * 		new value for 'taille '
     */
    public void setTaille(long taille) {
        this.taille = taille;
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
     * Setter accessor for attribute 'qualite'.
     * @param qualite
     * 		new value for 'qualite '
     */
    public void setQualite(int qualite) {
        this.qualite = qualite;
    }

    /**
     * Setter accessor for attribute 'bitrate'.
     * @param bitrate
     * 		new value for 'bitrate '
     */
    public void setBitrate(long bitrate) {
        this.bitrate = bitrate;
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
}
