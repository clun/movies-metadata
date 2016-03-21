package fr.clunven.docu.domain;

import fr.clunven.docu.media.MovieMetadata;

/**
 * Simple Episode.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class Episode extends Documentaire {
    
    private int serie;
    
    private int saison;
    
    private int episode;
    
    public Episode() {
    }
    
    public Episode(MovieMetadata mmd) {
        super(mmd);
    }

    /**
     * Getter accessor for attribute 'serie'.
     *
     * @return
     *       current value of 'serie'
     */
    public int getSerie() {
        return serie;
    }

    /**
     * Setter accessor for attribute 'serie'.
     * @param serie
     * 		new value for 'serie '
     */
    public void setSerie(int serie) {
        this.serie = serie;
    }

    /**
     * Getter accessor for attribute 'saison'.
     *
     * @return
     *       current value of 'saison'
     */
    public int getSaison() {
        return saison;
    }

    /**
     * Setter accessor for attribute 'saison'.
     * @param saison
     * 		new value for 'saison '
     */
    public void setSaison(int saison) {
        this.saison = saison;
    }

    /**
     * Getter accessor for attribute 'episode'.
     *
     * @return
     *       current value of 'episode'
     */
    public int getEpisode() {
        return episode;
    }

    /**
     * Setter accessor for attribute 'episode'.
     * @param episode
     * 		new value for 'episode '
     */
    public void setEpisode(int episode) {
        this.episode = episode;
    }

}
