/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import static video.game.Assets.player_FrozenMorty;
import video.game.KeyManager;
/**
 *
 * @author ErickFrank
 */
public class Player extends Item {
    
    private int direction;
    private int width;
    private final int height;
    private int speed;
    private final Game game;
    private int velX;
    private int velY;
    private boolean keyboard[];
    private int moveTick;
    private int maxTick;
    private boolean collision;
    private int lives;

   
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 1;
        this.velX = 0;
        this.velY = 0;
        this.keyboard = new boolean[4];
        this.moveTick = 0;
        this.maxTick = 25;
        this.collision = false;
        this.lives = 3;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
        
    }

    public int getMaxTick() {
        return maxTick;
    }

    public void setMaxTick(int maxTick) {
        this.maxTick = maxTick;
    }
    /**
     * To get the counting ticks of the moving
     * @return moveTick
     */
    public int getMoveTick() {
        return moveTick;
    }
    /**
     * To set the counting ticks of the moving animation
     * @param moveTick 
     */
    public void setMoveTick(int moveTick) {
        this.moveTick = moveTick;
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
    
    public Rectangle getPerimetro() {

      return new Rectangle(getX(), getY(),getWidth(), getHeight());
    }

     public boolean intersecta(Object obj) {
        
         return obj instanceof Player && getPerimetro().intersects( ((Player) obj).getPerimetro() );
    } 
    
    @Override
    public void tick() {
        //moving player depending on flags 
        // to determine the position if UP key pressed
        if(game.getKeyManager().up)
        {    

         if (!keyboard[0] && keyboard[2])
         {
            setVelY(getVelX());
            setVelX(0);
         }
         else if(!keyboard[0] && keyboard[3])
         {
            setVelY(-getVelX());
            setVelX(0);
         }
         else
         { 
             setVelY(getVelY() - getSpeed());
         
         }
         game.getKeyManager().setUp(false);
         keyboard[0] = true;
         keyboard[1] = keyboard[2] = keyboard[3] = false;
        }
        
        // to determine the position if  DOWN key pressed
        else if(game.getKeyManager().down)
        {
            
         if (!keyboard[1] && keyboard[2])
         {
            setVelY(-getVelX());
            setVelX(0);
         }
         else if(!keyboard[1] && keyboard[3])
         {
            setVelY(getVelX());
            setVelX(0);
         }
         else{
             setVelY(getVelY() + getSpeed());
             
             if(getVelY() == 0)
                setVelY(getVelY() + getSpeed());
         }
         game.getKeyManager().setDown(false);
         keyboard[1] = true;
         keyboard[0] = keyboard[2] = keyboard[3] = false;
        }
        
        // to determine the position if  LEFT key pressed
        else if(game.getKeyManager().left)
        {
            
         if (!keyboard[2] && keyboard[0])
         {
            setVelX(getVelY());
            setVelY(0);
         }
         else if(!keyboard[2] && keyboard[1])
         {
            setVelX(-getVelY());
            setVelY(0);
         }
         else{
             
             setVelX(getVelX() - getSpeed());
             
             if(getVelX() == 0)
                setVelX(getVelX() - getSpeed());
         }
         game.getKeyManager().setLeft(false);
         keyboard[2] = true;
         keyboard[0] = keyboard[1] = keyboard[3] = false;
        }
        
        // to determine the position if  RIGHT key pressed
        else if(game.getKeyManager().right)
        {
        if (!keyboard[3] && keyboard[0])
         {
            setVelX(-getVelY());
            setVelY(0);
         }
         else if(!keyboard[3] && keyboard[1])
         {
            setVelX(getVelY());
            setVelY(0);
         }
         else{
             setVelX(getVelX() + getSpeed());
             
             if(getVelX() == 0)
                setVelX(getVelX() + getSpeed());
             
         }
         game.getKeyManager().setRight(false);
         keyboard[3] = true;
         keyboard[0] = keyboard[1] = keyboard[2] = false;
        }
        

        // reset x position and y position if collision
                
        //right margin
        if( getX() + 100 >= game.getWidth()){
            setX(game.getWidth()- 100);
            setVelX(-getVelX());
            keyboard[2] = true;
            keyboard[0] = keyboard[1] = keyboard[3] = false;
            setCollision(true);
            setMoveTick(0);
        }
        //left margin
        else if (getX() <= -10){
            setX(-10);
            setVelX(-getVelX());
            keyboard[3] = true;
            keyboard[0] = keyboard[1] = keyboard[2] = false;
            setCollision(true);
            setMoveTick(0);
        }
        //top margin
        else if( getY() + 100 >= game.getHeight()){
            setY(game.getHeight()- 100);
            setVelY(-getVelY());
            keyboard[0] = true;
            keyboard[1] = keyboard[2] = keyboard[3] = false;
            setCollision(true);
            setMoveTick(0);
        }
        //bottom margin
        else if (getY() <= -10){
            setY(-10);
            setVelY(-getVelY());
            keyboard[1] = true;
            keyboard[0] = keyboard[2] = keyboard[3] = false;
            setCollision(true);
            setMoveTick(0);
        }
        
        setY(getY() + getVelY());  
        setX(getX() + getVelX()); 
        
        
        
        if(isCollision())
        {
            setMoveTick(getMoveTick()+1);
            
            if(getMoveTick() > getMaxTick())
            {
             setCollision(false);
            }
        }
        
        
        
    }

    @Override
    public void render(Graphics g){

        if(isCollision())
        {
             g.drawImage(Assets.player_FrozenMorty[6], getX(), getY(), getWidth(), getHeight(), null);
        }
        else g.drawImage(Assets.player_FrozenMorty[3], getX(), getY(), getWidth(), getHeight(), null);
        
        
    }

   
    
}
