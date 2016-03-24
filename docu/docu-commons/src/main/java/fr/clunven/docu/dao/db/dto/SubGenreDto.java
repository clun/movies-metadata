package fr.clunven.docu.dao.db.dto;

public class SubGenreDto {

    private GenreDto subgenre;
    
    private int nb;

    /**
     * Getter accessor for attribute 'subgenre'.
     *
     * @return
     *       current value of 'subgenre'
     */
    public GenreDto getSubgenre() {
        return subgenre;
    }

    /**
     * Setter accessor for attribute 'subgenre'.
     * @param subgenre
     * 		new value for 'subgenre '
     */
    public void setSubgenre(GenreDto subgenre) {
        this.subgenre = subgenre;
    }

    /**
     * Getter accessor for attribute 'nb'.
     *
     * @return
     *       current value of 'nb'
     */
    public int getNb() {
        return nb;
    }

    /**
     * Setter accessor for attribute 'nb'.
     * @param nb
     * 		new value for 'nb '
     */
    public void setNb(int nb) {
        this.nb = nb;
    }
}
