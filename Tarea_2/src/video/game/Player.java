
package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Math.random;

/**
 *
 * @author ErickFrank
 */
public class Player extends Item {

    private int width;
    private int height;
    private int speed;
    private Game game;
    private int countFrames;
    private boolean intersects;
    private int lives;

    public Player(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 10;
        this.countFrames = 0;
        this.intersects = false;
        this.lives = 3;
       
    }
    /**
     * To get the lives of the player
     * @return lives
     */
    public int getLives() {
        return lives;
    }
    /**
     * To set the lives of the player
     * @param lives 
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
    /**
     * To get the speed of the player
     * @return speed 
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * To set the speed of the player
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /**
     * 
     * @return intersects
     */
    
    public boolean isIntersects() {
        return intersects;
    }

    public void setIntersects(boolean intersects) {
        this.intersects = intersects;
    }
    
    public int getCountFrames() {
        return countFrames;
    }

    public void setCountFrames(int countFrames) {
        this.countFrames = countFrames;
    }


    /**
     * To set the width of the game window
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
     public Rectangle getPerimetro() {

            return new Rectangle(getX(), getY(),getWidth(), getHeight());
        }

     public boolean intersecta(Enemy obj) {

            return getPerimetro().intersects(obj.getPerimetro());
        }
     public void reboot()
    {
          setLives(getLives()-1);
          setX((int) (random() * ( 2 * getWidth() / 3 ) +700));
          setY((int) (random() * ( getHeight()/6 ) ) + 100);
    }
     
    @Override
    public void tick() {

        setCountFrames(getCountFrames() + 1);

        if (game.getMouseManager().isIzquierdo()) {
            setX(game.getMouseManager().getX());
            setY(game.getMouseManager().getY());
        }
         
        //moving player depending on flags
        
        if (game.getKeyManager().up) {
            setY(getY() - speed);
        }
        if (game.getKeyManager().down) {
            setY(getY() + speed);
        }
        if (game.getKeyManager().left) {
            setX(getX() - speed);
        }
        if (game.getKeyManager().right) {
            setX(getX() + speed);
        }
        
        // reset x position and y position if collision
        
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        } else if (getX() <= -30) {
            setX(-30);
        }
        
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        } else if (getY() <= -20) {
            setY(-20);
        }

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.planet, getX(), getY(), getWidth(), getHeight(), null);

    }

}
