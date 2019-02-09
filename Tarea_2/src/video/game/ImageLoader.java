/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ErickFrank
 */
public class ImageLoader {
    /**
     * to get an image from the file path
     * @param path is the path of the file
     * @return the <b>BufferedImage</b> object
     */
    public static BufferedImage loadImage(String path){
        BufferedImage bi = null;
        try{
            bi = ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException ioe){
            System.out.println("Error loading image" + path + ioe.toString());
            System.exit(1);
        }
        return bi;
    }  
}
