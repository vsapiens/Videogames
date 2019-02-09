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

    
    public Enemy(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 2;
        this.countFrames = 0;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getCountFrames() {
        return countFrames;
    }

    public void setCountFrames(int countFrames) {
        this.countFrames = countFrames;
    }

    public Rectangle getPerimetro() {

          return new Rectangle(getX(), getY(),getWidth(), getHeight());
        }

     public boolean intersecta(Player obj) {

            return getPerimetro().intersects(obj.getPerimetro());
        }
     
    public void reboot()
    {
          setSpeed(getSpeed()+2);
          setX((int) (random() * ( 3 * getWidth() / 5)));
          setY((int) (random() * ( getHeight()  ) ) );
    }
    

    
    public void follow(int xPos, int yPos)
    {
      setX(getX() <= xPos ? getX()+getSpeed():getX()-getSpeed());
      setY(getY() <= yPos ? getY()+getSpeed():getY()-getSpeed());
    }
     
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

    @Override
    public void render(Graphics g) {
        
        g.drawImage(Assets.asteroid, getX(), getY(),getWidth(), getHeight(), null);
     
    }
    
}
