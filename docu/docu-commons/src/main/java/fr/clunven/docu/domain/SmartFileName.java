package fr.clunven.docu.domain;

import java.util.regex.Pattern;

/**
 * Help to parse fileName.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class SmartFileName {
    
    /** Year pattern. */
    private static Pattern PATTERN_YEAR = Pattern.compile("^.*\\([0-9]{4}\\).*$");
    
    /** Year pattern. */
    private static Pattern PATTERN_VO = Pattern.compile("^.*\\(vo\\).*$");
    
     /** store fileName. */
    private String filename;
    
    /** year if present (KKKK). */
    private int annee = -1;
    
    /** check annee. */
    private boolean anneeChecked = false;
    
    /** if vo is present... */
    private boolean vo = false;
    
    /** if vo has been cehcked. */
    private boolean voChecked = false;
    
    /** If the number exist; compute it. */
    private int number = -1;
    
    /** here is the filename. */
    private String titre = "";
    
    /** read extension if not directory. */
    private String extension;
    
    /**
     * Constructor and parsing.
     * 
     * @param fileName
     *      target fileName
     */
    public SmartFileName(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("FileName cannot be null");
        }
        this.filename = fileName;
        
        // Parcours caractere par caractere
        titre = fileName;
        
        // Commence par le chiffre => number
        if (startByNumber()) {
            number = Integer.parseInt(fileName.substring(0,2));
            titre = filename.substring(4);
        }        
        // Remove extension
        if (getExtension() != null) {
            titre = titre.substring(0, titre.lastIndexOf("."));
        }        
        // Vo
        if (isVo()) {
            titre = titre.replaceAll("\\(vo\\)", "");
        }        
        // Annee
        if (getAnnee() != -1) {
            titre = titre.replaceAll("\\(" + annee + "\\)", "");
        }        
        titre = titre.replaceAll("-- ", "").trim();
    }

    /**
     * Getter accessor for attribute 'extension'.
     *
     * @return
     *       current value of 'extension'
     */
    public String getExtension() {
        if (extension == null) {
            if (filename.lastIndexOf(".") != -1) {
                extension = filename.substring(filename.lastIndexOf(".")+1).toUpperCase();
            }
        }
        return extension;
    }  

    /**
     * Getter accessor for attribute 'filename'.
     *
     * @return
     *       current value of 'filename'
     */
    public String getFilename() {
        return filename;
    }

    public boolean containsAnnee() {
        return PATTERN_YEAR.matcher(filename).matches();
    }
    /**
     * Getter accessor for attribute 'annee'.
     *
     * @return
     *       current value of 'annee'
     */
    public int getAnnee() {
        if (!anneeChecked) {
            if (containsAnnee()) {
                int offset = 0;
                String strSearch = filename;
                while (offset != -1 && annee == -1) {
                    offset = strSearch.indexOf("(");
                    if (
                       Character.isDigit(strSearch.charAt(offset+1)) &&
                       Character.isDigit(strSearch.charAt(offset+2)) &&
                       Character.isDigit(strSearch.charAt(offset+3)) &&
                       Character.isDigit(strSearch.charAt(offset+4)) &&
                       strSearch.charAt(offset+5) == ')') {
                       String ex = strSearch.substring(offset+1, offset+5);
                       annee = Integer.parseInt(ex);
                    } else {
                        strSearch = strSearch.substring(offset+1);
                    }
                }
            }
            anneeChecked = true;
        }
        return annee;
    }
    
    /**
     * Getter accessor for attribute 'vo'.
     *
     * @return
     *       current value of 'vo'
     */
    public boolean isVo() {
        if (!voChecked) {
            vo = PATTERN_VO.matcher(filename).matches();
            voChecked = true;
        }
        return vo;
    }
    
    /**
     * Check if starting by request number.
     *
     * @return
     *      if the filename start by a number
     */
    public boolean startByNumber() {
        return Character.isDigit(filename.charAt(0)) && 
               Character.isDigit(filename.charAt(1));
    }

    /**
     * Getter accessor for attribute 'number'.
     *
     * @return
     *       current value of 'number'
     */
    public int getNumber() {
        return number;
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

}
