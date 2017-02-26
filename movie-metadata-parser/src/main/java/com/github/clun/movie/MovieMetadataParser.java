package com.github.clun.movie;

import java.io.File;

import com.github.clun.movie.domain.Audio;
import com.github.clun.movie.domain.General;
import com.github.clun.movie.domain.MovieMetadata;
import com.github.clun.movie.domain.Video;
import com.github.clun.movie.jna.MediaInfo;

/**
 * Informations relatives to a movie file.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class MovieMetadataParser {
    
    /**
     * Singleton pattern, avoid synchronized using static declaration.
     */
    public enum MovieMetadataParserHolder {
        INSTANCE;
        MovieMetadataParser PARSER = new MovieMetadataParser();
    }
    
    /**
     * Singleton pattern, holder.
     *
     * @return
     */
    public static MovieMetadataParser getInstance() {
        return MovieMetadataParserHolder.INSTANCE.PARSER;
    }

    /**
     * Default Parser.
     */
    private MovieMetadataParser() {
    }
    
    /**
     * Read Metadata of file
     */
    public MovieMetadata parseFile(String path) {
        File file         = new File(path);
        MediaInfo info    = new MediaInfo();
        MovieMetadata mm = new MovieMetadata(path);
        try {
            info.open(file);
            for (General gKey : General.values()) {
                String val = info.get(MediaInfo.StreamKind.General, 0, gKey.getKey(),  MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                if (val != null && !val.isEmpty()) {
                    mm.getGeneralKeys().put(gKey, val);
                }
            }
            for (Audio aKey : Audio.values()) {
                String val = info.get(MediaInfo.StreamKind.Audio, 0, aKey.getKey(),  MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                if (val != null && !val.isEmpty()) {
                    mm.getAudioKeys().put(aKey, val);
                }
            }
            for (Video vKey : Video.values()) {
                String val = info.get(MediaInfo.StreamKind.Video, 0, vKey.getKey(),  MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                if (val != null && !val.isEmpty()) {
                    mm.getVideoKeys().put(vKey, val);
                }
            }
        } finally {            
            info.close();   
        }
        return mm;
    }

}
