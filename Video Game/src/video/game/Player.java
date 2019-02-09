/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;
import static video.game.Assets.player_FrozenMorty;

/**
 *
 * @author ErickFrank
 */
public class Player extends Item {
    
    private int direction;
    private int width;
    private int height;
    private int speed;
    private Game game;
    private int countFrames;
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 10;
        this.countFrames = 0;
    }

    public int getCountFrames() {
        return countFrames;
    }

    public void setCountFrames(int countFrames) {
        this.countFrames = countFrames;
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
    
    @Override
    public void tick() {
        
        setCountFrames(getCountFrames()+1);
        
        //moving player depending on flags
        if(game.getKeyManager().up)
        {
         setY(getY()-speed);   
        }
        if(game.getKeyManager().down)
        {
         setY(getY()+speed);   
        }
        if(game.getKeyManager().left)
        {
         setX(getX()-speed);   
        }
        if(game.getKeyManager().right)
        {
         setX(getX()+speed);   
        }
        
        
        // reset x position and y position if collision
       
        //setX(getX() + getDirection());
        
        if( getX() + 60 >= game.getWidth()){
            setX(game.getWidth()- 60);
            setDirection(-1);   
        }
        else if (getX() <= -30){
            setX(-30);
            setDirection(1);
        }
        
         if( getY() + 80 >= game.getHeight()){
            setY(game.getHeight()- 80);
        }
        else if (getY() <= -20){
            setY(-20);
        }
        
    }

    @Override
    public void render(Graphics g) {
        
        
     g.drawImage(Assets.player_FrozenMorty[0], getX(), getY(), getWidth(), getHeight(), null);
     
     
     
    }

   
    
}
