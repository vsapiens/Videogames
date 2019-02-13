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
    public static BufferedImage asteroid;   //to store the asteroid image
    public static BufferedImage lives;      //to store the live image
    public static BufferedImage gameover;   // to store the gameover image
    public static SoundClip bomb;           // to store the sound of the bomb

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/space.png");
        planet = ImageLoader.loadImage("/images/planet.png");
        asteroid = ImageLoader.loadImage("/images/asteroid.png");
        lives = ImageLoader.loadImage("/images/lives.png");
        gameover = ImageLoader.loadImage("/images/gameover_1.png");
        bomb = new SoundClip("/sounds/blip.wav");
    }
}