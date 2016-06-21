package fr.clunven.docu.domain;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Permet de parser un répertoire de série.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class SmartSerieName {

    /** Year pattern. */
    private static Pattern PATTERN_YEAR = Pattern.compile("^.*\\([0-9]{4}\\).*$");
    
    /** Year pattern. */
    private static Pattern PATTERN_SLOTYEAR = Pattern.compile("^.*\\([0-9]{4}-[0-9]{4}\\).*$");

    /** Year pattern. */
    private static Pattern PATTERN_VO = Pattern.compile("^.*\\(vo\\).*$");

    /** store fileName. */
    private String filename;

    /** year if present (KKKK). */
    private int anneeStart = -1;

    /** year if present (KKKK-YYYY). */
    private int anneeEnd = -1;

    /** check annee. */
    private boolean anneeChecked = false;

    /** if vo is present... */
    private boolean vo = false;

    /** if vo has been cehcked. */
    private boolean voChecked = false;

    /** here is the filename. */
    private String titre = "";
    
    /** relative file. */
    private File folder;

    /**
     * Constructor for folder.
     *
     * @param currentFile
     *      current folder
     */
    public SmartSerieName(File currentFile) {
        this(currentFile.getName());
        this.folder = currentFile;
    }
    
    /**
     * Constructor and parsing.
     * 
     * @param fileName
     *            target fileName
     */
    public SmartSerieName(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("FileName cannot be null");
        }
        this.filename = fileName;

        // Parcours caractere par caractere
        titre = filename.replaceAll("\\[SERIE\\] - ", "");

        // Vo
        if (isVo()) {
            titre = titre.replaceAll("\\(vo\\)", "");
        }

        parseAnnee();
        titre = titre.replaceAll("-- ", "");
        
        if (containsSingleAnnee()) {
            titre = titre.replaceAll("\\(" + anneeStart + "\\)", "");
        }
        
        if (containsSlotAnnee()) {
            titre = titre.replaceAll("\\(" + anneeStart + "-" + anneeEnd + "\\)", "");
        }
        titre = titre.trim();
    }

    /**
     * Getter accessor for attribute 'filename'.
     *
     * @return current value of 'filename'
     */
    public String getFilename() {
        return filename;
    }

    public boolean containsSingleAnnee() {
        return PATTERN_YEAR.matcher(filename).matches();
    }
    
    public boolean containsSlotAnnee() {
        return PATTERN_SLOTYEAR.matcher(filename).matches();
    }

    /**
     * Getter accessor for attribute 'annee'.
     *
     * @return current value of 'annee'
     */
    public void parseAnnee() {
        if (!anneeChecked) {
            if (containsSlotAnnee()) {
                int offset = 0;
                String strSearch = filename;
                while (offset != -1 && anneeStart == -1) {
                    offset = strSearch.indexOf("(");
                    if (Character.isDigit(strSearch.charAt(offset + 1)) 
                            && Character.isDigit(strSearch.charAt(offset + 2))
                            && Character.isDigit(strSearch.charAt(offset + 3)) 
                            && Character.isDigit(strSearch.charAt(offset + 4))
                            && (strSearch.charAt(offset + 5) == '-')
                            && Character.isDigit(strSearch.charAt(offset + 6))
                            && Character.isDigit(strSearch.charAt(offset + 7))
                            && Character.isDigit(strSearch.charAt(offset + 8))
                            && Character.isDigit(strSearch.charAt(offset + 9))
                            && (strSearch.charAt(offset + 10) == ')')
                            ) {
                        String ex = strSearch.substring(offset + 1, offset + 5);
                        anneeStart = Integer.parseInt(ex);
                        String ex2 = strSearch.substring(offset + 6, offset + 10);
                        anneeEnd   = Integer.parseInt(ex2);
                    } else {
                        strSearch = strSearch.substring(offset + 1);
                    }
                }
                
            } else if (containsSingleAnnee()) {
                int offset = 0;
                String strSearch = filename;
                while (offset != -1 && anneeStart == -1) {
                    offset = strSearch.indexOf("(");
                    if (Character.isDigit(strSearch.charAt(offset + 1)) && Character.isDigit(strSearch.charAt(offset + 2))
                            && Character.isDigit(strSearch.charAt(offset + 3)) && Character.isDigit(strSearch.charAt(offset + 4))
                            && strSearch.charAt(offset + 5) == ')') {
                        String ex = strSearch.substring(offset + 1, offset + 5);
                        anneeStart = Integer.parseInt(ex);
                        anneeEnd   = anneeStart;
                    } else {
                        strSearch = strSearch.substring(offset + 1);
                    }
                }
            }
        }
        
    }

    /**
     * Getter accessor for attribute 'vo'.
     *
     * @return current value of 'vo'
     */
    public boolean isVo() {
        if (!voChecked) {
            vo = PATTERN_VO.matcher(filename).matches();
            voChecked = true;
        }
        return vo;
    }

    /**
     * Getter accessor for attribute 'titre'.
     *
     * @return current value of 'titre'
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Getter accessor for attribute 'anneeStart'.
     *
     * @return current value of 'anneeStart'
     */
    public int getAnneeStart() {
        return anneeStart;
    }

    /**
     * Getter accessor for attribute 'anneeEnd'.
     *
     * @return current value of 'anneeEnd'
     */
    public int getAnneeEnd() {
        return anneeEnd;
    }

    /**
     * Getter accessor for attribute 'folder'.
     *
     * @return
     *       current value of 'folder'
     */
    public File getFolder() {
        return folder;
    }

    /**
     * Setter accessor for attribute 'folder'.
     * @param folder
     * 		new value for 'folder '
     */
    public void setFolder(File folder) {
        this.folder = folder;
    }

}
