package fr.clunven.docu.service;

import java.io.File;
import java.util.function.Predicate;

public class BatchPredicates {
    
    public static Predicate< File > validMovieFiles() {
        return f -> f.isFile() && 
                   !f.getName().endsWith("srt") && 
                   !f.getName().endsWith("txt");
    }
    
    public static Predicate< File > validFolderDocumentaires() {
        return f -> f.isDirectory() && 
              !f.getName().startsWith("[SERIE] - ") &&
              !f.getName().startsWith("--") &&
              !f.getName().startsWith("Saison") &&
              !Character.isDigit(f.getName().charAt(0));
    }
    
    public static Predicate<File> sousGenreFolder() {
        return f->f.isDirectory() && f.getName().startsWith("--");
    }

}
