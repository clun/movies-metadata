package fr.clunven.test;

import org.junit.Test;

import fr.clunven.mediainfo.domain.MovieMetadata;

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
    }

}
