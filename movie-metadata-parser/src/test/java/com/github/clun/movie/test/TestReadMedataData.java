package com.github.clun.movie.test;

import org.junit.Test;

import com.github.clun.movie.MovieMetadataParser;
import com.github.clun.movie.domain.Audio;
import com.github.clun.movie.domain.General;
import com.github.clun.movie.domain.MovieMetadata;
import com.github.clun.movie.domain.Video;

/**
 * Parse file list
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public class TestReadMedataData {
   
    private static final String FOLDER_VIDEO = "./src/test/resources/";
    
    private void displayMetadata(String fileName) {
        System.out.println("====================" + fileName + " ======================================");
        MovieMetadata video = MovieMetadataParser.getInstance().parseFile(FOLDER_VIDEO + fileName);
        System.out.println("width=" + video.getVideoWidth().get());
        System.out.println("height=" + video.getVideoHeight().get());
        System.out.println(video.toString());
    }
    
    @Test
    public void testReadMeta() {
        // Sample display
        displayMetadata("small.3gp");
        displayMetadata("small.webm");
        displayMetadata("small.mp4");
        displayMetadata("small.ogv");
        displayMetadata("small.flv");
        displayMetadata("small.mkv");
        
        // Sample Usage
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
