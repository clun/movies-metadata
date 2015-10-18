package fr.clunven.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

public class BatchConvertToBase64 {
    
    private static String SOURCE_PATH = "C:/Users/cel/Pictures/affiches";
    
    private static String UPDATE_QUERY = "UPDATE DOCUMENTAIRE SET IMAGE =";
    
    private static String UPDATE_QUERY2 = " WHERE ID = ";
    
    public static void main (String args[]) throws IOException {
        
        File[] files = new File(SOURCE_PATH).listFiles();
        FileWriter fw = new FileWriter(new File("C:/Users/cel/out.sql"));
        for (File img : files) {
           int id = Integer.valueOf(img.getName().replaceAll("\\.jpg", "").replaceAll("Filmotech_", ""));
           fw.write("/* " + id + " */ \r\n");
           String query = UPDATE_QUERY + "\"" + encodeToString(ImageIO.read(img), "jpg") + "\"" + UPDATE_QUERY2 + id;
           fw.write(query + ";\r\n");
           System.out.println(id);
        }
        fw.close();
    }

    
    /**
     * Encode image to string
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            return new String(Base64.getEncoder().encode(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return imageString;
    }
    
   
}