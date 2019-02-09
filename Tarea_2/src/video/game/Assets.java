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

    public static BufferedImage background;     //to store background image
    public static BufferedImage planet;     //to store player image
    public static BufferedImage asteroid;
    public static BufferedImage lives;
    public static BufferedImage gameover;

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/space.png");
        planet = ImageLoader.loadImage("/images/planet.png");
        asteroid = ImageLoader.loadImage("/images/asteroid.png");
        lives = ImageLoader.loadImage("/images/lives.png");
        gameover = ImageLoader.loadImage("/images/gameover_1.png");
    }
}