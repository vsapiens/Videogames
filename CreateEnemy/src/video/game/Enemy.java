/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author ErickFrank
 */
public class Enemy extends Item {
    
    private int direction;
    private int width;
    private final int height;
    private int speed;
    private final Game game;
    private int velX;
    private int velY;
    private boolean collision;

   
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 1;
        this.velX = 0;
        this.velY = 0;
        this.collision = false;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
        
    }


    /**
     * To get the direction of the player
     * @return direction
     */
     private int getDirection() {
        return direction;
    }
   
     /**
      * To set the direction of the player
      * @param direction 
      */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    /**
     * To set the width of the game window
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height 
     */
    public int getHeight() {
        return height;
    }
    /**
     * To get the speed of the player
     * @return speed of the player
     */
    public int getSpeed() {
        return speed * getDirection();
    }
    /**
     * To set the speed of the player
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /**
     * To get the velocity in the X axis
     * @return velX
     */
    public int getVelX() {
        return velX;
    }
    /**
     * To get the velocity in the Y axis
     * @return velY
     */
    public int getVelY() {
        return velY;
    }
    /**
     * To set the velocity in the X axis
     * @param velX 
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }
    /**
     * To set the velocity in the Y axis
     * @param velY 
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }
     
     
    @Override
    public void tick() {
        
        
        
        // reset x position and y position if collision
                
        //right margin
        if( getX() + 100 >= game.getWidth()){
            setX(game.getWidth()- 100);
            setVelX(-getVelX());
            setCollision(true);
        }
        //left margin
        else if (getX() <= -10){
            setX(-10);
            setVelX(-getVelX());
            setCollision(true);
        }
        //top margin
        else if( getY() + 100 >= game.getHeight()){
            setY(game.getHeight()- 100);
            setVelY(-getVelY());
            setCollision(true);
        }
        //bottom margin
        else if (getY() <= -10){
            setY(-10);
            setVelY(-getVelY());
            setCollision(true);
        }
        
        
    }

    @Override
    public void render(Graphics g){

    g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);

    }
}