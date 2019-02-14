/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Math.random;

/**
 *
 * @author ErickFrank
 */
public class Enemy extends Item{
    private int width;
    private int height;
    private int speed;
    private Game game;
    private int countFrames;

    /**
     * Initializes the enemy with a specific x and y with a height and width of the image
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game 
     */
    public Enemy(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 2;
        this.countFrames = 0;
    }
    /**
     * To get the width of the enemy
     * @return 
     */
    public int getWidth() {
        return width;
    }
    /**
     * To set the width of the image
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * To get the height of the image
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     * To set the height of the image
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * To get the speed of the image
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * To set the speed of the image
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /**
     * To get the instance of the game
     * @return game
     */
    public Game getGame() {
        return game;
    }
    /**
     * To count the frames of the game
     * @return 
     */
    public int getCountFrames() {
        return countFrames;
    }
    /**
     * To set the count frames of the game
     * @param countFrames 
     */
    public void setCountFrames(int countFrames) {
        this.countFrames = countFrames;
    }
    /**
     * To get the perimeter of the rectangle of the object
     * @return Rectangle
     */
    public Rectangle getPerimetro() {

          return new Rectangle(getX(), getY(),getWidth(), getHeight());
        }
    /**
     * To know if the other object intersects this object
     * @param obj
     * @return 
     */
     public boolean intersecta(Player obj) {

            return getPerimetro().intersects(obj.getPerimetro());
        }
     /**
      * To reboot the position of the enemy object
      */
    public void reboot()
    {
          setSpeed(getSpeed()+2);
          setX((int) (random() * ( 3 * getWidth() / 5)));
          setY((int) (random() * ( getHeight()  ) ) );
    }
    
    /**
     * To move the object to the xPos and yPos
     * @param xPos
     * @param yPos 
     */
    public void follow(int xPos, int yPos)
    {
      setX(getX() <= xPos ? getX()+getSpeed():getX()-getSpeed());
      setY(getY() <= yPos ? getY()+getSpeed():getY()-getSpeed());
    }
    /**
     * sets the movement of the object
     */
@Override
    public void tick() {
        
    
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
    /**
     * to render the image of the enemy
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        
        g.drawImage(Assets.asteroid, getX(), getY(),getWidth(), getHeight(), null);
     
    }
    
}
