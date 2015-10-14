package fr.clunven.mediainfo.domain;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import fr.clunven.mediainfo.jna.MediaInfo;

/**
 * Informations relatives to a movie file.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class MovieMetadata {
    
    /** file path. */
    private String path;
    
    /** metadata relative to general file. */
    private Map < General, String> generalKeys = new HashMap<>();
    
    /** metadata relative to audio track. */
    private Map < Audio, String> audioKeys = new HashMap<>();

    /** metadata relative to video track. */
    private Map < Video, String> videoKeys = new HashMap<>();
    
    /** {@inheritDoc} */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\nGENERAL:\r\n");
        for (General g : generalKeys.keySet()) {
            sb.append(g + "=" + generalKeys.get(g) + "\r\n");
        }
        
        sb.append("\r\nAUDIO:\r\n");
        for (Audio g : audioKeys.keySet()) {
            sb.append(g + "=" + audioKeys.get(g) + "\r\n");
        }
        
        sb.append("\r\nVIDEO:\r\n");
        for (Video g : videoKeys.keySet()) {
            sb.append(g + "=" + videoKeys.get(g) + "\r\n");
        }
        return sb.toString();
    }
    
    /**
     * Read Metadata of file
     */
    private void parseFile() {
        File file         = new File(path);
        MediaInfo info    = new MediaInfo();
        info.open(file);
        for (General gKey : General.values()) {
            String val = info.get(MediaInfo.StreamKind.General, 0, gKey.getKey(),  MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            if (val != null && !val.isEmpty()) {
                getGeneralKeys().put(gKey, val);
            }
        }
        for (Audio aKey : Audio.values()) {
            String val = info.get(MediaInfo.StreamKind.Audio, 0, aKey.getKey(),  MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            if (val != null && !val.isEmpty()) {
                getAudioKeys().put(aKey, val);
            }
        }
        for (Video vKey : Video.values()) {
            String val = info.get(MediaInfo.StreamKind.Video, 0, vKey.getKey(),  MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            if (val != null && !val.isEmpty()) {
                getVideoKeys().put(vKey, val);
            }
        }
        info.close();
    }
    
    public MovieMetadata(String filePath) {
        this.path = filePath;
        parseFile();
    }

    /**
     * Getter accessor for attribute 'path'.
     *
     * @return
     *       current value of 'path'
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter accessor for attribute 'path'.
     * @param path
     * 		new value for 'path '
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Getter accessor for attribute 'generalKeys'.
     *
     * @return
     *       current value of 'generalKeys'
     */
    public Map<General, String> getGeneralKeys() {
        return generalKeys;
    }
    
    public Optional<String> get(General key) {
        return Optional.ofNullable(getGeneralKeys().get(key));
    }
    public Optional<String> get(Audio key) {
        return Optional.ofNullable(getAudioKeys().get(key));
    }
    public Optional<String> get(Video key) {
        return Optional.ofNullable(getAudioKeys().get(key));
    }

    /**
     * Setter accessor for attribute 'generalKeys'.
     * @param generalKeys
     * 		new value for 'generalKeys '
     */
    public void setGeneralKeys(Map<General, String> generalKeys) {
        this.generalKeys = generalKeys;
    }

    /**
     * Getter accessor for attribute 'audioKeys'.
     *
     * @return
     *       current value of 'audioKeys'
     */
    public Map<Audio, String> getAudioKeys() {
        return audioKeys;
    }

    /**
     * Setter accessor for attribute 'audioKeys'.
     * @param audioKeys
     * 		new value for 'audioKeys '
     */
    public void setAudioKeys(Map<Audio, String> audioKeys) {
        this.audioKeys = audioKeys;
    }

    /**
     * Getter accessor for attribute 'videoKeys'.
     *
     * @return
     *       current value of 'videoKeys'
     */
    public Map<Video, String> getVideoKeys() {
        return videoKeys;
    }

    /**
     * Setter accessor for attribute 'videoKeys'.
     * @param videoKeys
     * 		new value for 'videoKeys '
     */
    public void setVideoKeys(Map<Video, String> videoKeys) {
        this.videoKeys = videoKeys;
    }

}
