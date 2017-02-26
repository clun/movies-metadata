package fr.clunven.test;

import org.junit.Test;

import fr.clunven.mediainfo.domain.Audio;
import fr.clunven.mediainfo.domain.General;
import fr.clunven.mediainfo.domain.MovieMetadata;
import fr.clunven.mediainfo.domain.Video;

/**
 * Parse file list
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public class TestReadMedataData {
   
    @Test
    public void testReadMeta() {
        System.out.println("==================== 3GP ======================================");
        System.out.println(new MovieMetadata("./src/test/resources/small.3gp").toString());
        
        System.out.println("==================== WEBM ======================================");
        System.out.println(new MovieMetadata("./src/test/resources/small.webm").toString());
        
        System.out.println("==================== MP4 ======================================");
        System.out.println(new MovieMetadata("./src/test/resources/small.mp4").toString());
        
        System.out.println("==================== OGG ======================================");
        System.out.println(new MovieMetadata("./src/test/resources/small.ogv").toString());
        
        System.out.println("==================== FLV ======================================");
        System.out.println(new MovieMetadata("./src/test/resources/small.flv").toString());
        
        System.out.println("==================== MKV ======================================");
        System.out.println(new MovieMetadata("./src/test/resources/small.mkv").toString());
        
        
        MovieMetadata movieMedataData = new MovieMetadata("./src/test/resources/small.mkv");
        movieMedataData.get(General.FORMAT);
        movieMedataData.get(Video.DURATION_STRING);
        movieMedataData.get(Video.WIDTH_STRING);
        movieMedataData.get(Video.HEIGHT_STRING);
        movieMedataData.get(Video.BITRATE_STRING);
        movieMedataData.get(Audio.COMPRESSION_RATIO);
        //...
        
    }

}
