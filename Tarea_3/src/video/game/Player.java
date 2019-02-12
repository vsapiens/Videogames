package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author ErickFrank
 */
public class Player extends Item {

    private int width;
    private final int height;
    private int speed;
    private final Game game;
    private boolean collision;
    private int score;
    private int lives;
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
        this.collision = false;
        this.score = 0;
        this.lives = 5;
        
    }
    /**
     * To get the score of the player
     * @return score
     */
    public int getScore() {
        return score;
    }
    /**
     * To set the score of the player
     * @param score 
     */
        public void setScore(int score) {
        this.score = score;
    }
    /**
     * To get the lives of the player
     * @return lives
     */
    public int getLives() {
        return lives;
    }
    /**
     * To set the lives of the payer
     * @param lives 
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
    /**
     * To get the collision state of the player
     * @return collision
     */
    public boolean isCollision() {
        return collision;
    }
    /**
     * To set the collision state of the player
     * @param collision 
     */
    public void setCollision(boolean collision) {
        this.collision = collision;

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
    /**
     * To get the speed of the player
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }
    
    
    /**
     * To set the speed of the player
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    
     public Rectangle getPerimetro() {

            return new Rectangle(getX(), getY(),getWidth(), getHeight());
        }

     public boolean intersecta(Ball obj) {

            return getPerimetro().intersects(obj.getPerimetro());
        }
     
     
    @Override
    public void tick() {
        //moving player depending on flags 
        
        ///moving player depending on flags
        
        if (game.getKeyManager().up) {
            setY(getY() - getSpeed());
        }
        if (game.getKeyManager().down) {
            setY(getY() + getSpeed());
        }
        if (game.getKeyManager().left) {
            setX(getX() - getSpeed());
        }
        if (game.getKeyManager().right) {
            setX(getX() + getSpeed());
        }

        // reset x position and y position if collision
        
        //right margin
        if (getX() + 100 >= game.getWidth()) {
            setX(game.getWidth() - 100);
        } //left margin
        else if (getX() <= -10) {
            setX(-10);
        } //top margin
        else if (getY() + 100 >= game.getHeight()) {
            setY(game.getHeight() -100);
        } //bottom margin
        else if (getY() <= -10) {
            setY(-10);
        }

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }

}
