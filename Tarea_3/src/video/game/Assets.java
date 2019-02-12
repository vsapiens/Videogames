/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.image.BufferedImage;

/**
 *
 * @author ErickFrank
 */
public class Assets {
    public static BufferedImage background; //to store background image
    public static BufferedImage player;//to store player image
    public static BufferedImage lives; //to store images of live
    public static BufferedImage gameover; //to store images of live
    
    /**
     * initializing the images of the game
     */
    public static void init(){
     background = ImageLoader.loadImage("/images/Background.jpg");
     player = ImageLoader.loadImage("/images/Player.png");
     lives = ImageLoader.loadImage("/images/lives.png"); 
     gameover = ImageLoader.loadImage("/images/gameover.jpg"); 
    }
}
