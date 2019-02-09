/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

/**
 *
 * @author ErickFrank
 */
public class VideoGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creating a Game object
        Game g = new Game("Juego",1024,576);
        //initalizing the game
        g.start();
    }
    
}
