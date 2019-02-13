
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
