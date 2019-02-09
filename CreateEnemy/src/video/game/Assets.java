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
    public static BufferedImage player_FrozenMorty[];
    public static BufferedImage background; //to store background image
    public static BufferedImage player;//to store player image
    public static BufferedImage enemy;//to store player image
    
    
    /**
     * initializing the images of the game
     */
    public static void init(){
     player_FrozenMorty = new BufferedImage[12];
     background = ImageLoader.loadImage("/images/Background.jpg");
     enemy = ImageLoader.loadImage("/images/Player_Left.png");
     player_FrozenMorty[0] = ImageLoader.loadImage("/images/Player_Left1.png");
     player_FrozenMorty[1] = ImageLoader.loadImage("/images/Player_Left2.png");
     player_FrozenMorty[2] = ImageLoader.loadImage("/images/Player_Left3.png");
     player_FrozenMorty[3] = ImageLoader.loadImage("/images/Player_Right1.png");
     player_FrozenMorty[4] = ImageLoader.loadImage("/images/Player_Right2.png");
     player_FrozenMorty[5] = ImageLoader.loadImage("/images/Player_Right3.png");
     player_FrozenMorty[6] = ImageLoader.loadImage("/images/Player_Up1.png");
     player_FrozenMorty[7] = ImageLoader.loadImage("/images/Player_Up2.png");
     player_FrozenMorty[8] = ImageLoader.loadImage("/images/Player_Up3.png");
     player_FrozenMorty[9] = ImageLoader.loadImage("/images/Player_Down1.png");
     player_FrozenMorty[10] = ImageLoader.loadImage("/images/Player_Down2.png");
     player_FrozenMorty[11] = ImageLoader.loadImage("/images/Player_Down3.png");
     
    }
}
