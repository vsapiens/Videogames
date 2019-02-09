/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;

/**
 *
 * @author ErickFrank
 */
public class Balls extends Item {
    
    private final Game game;
    private int width;
    private int height;
    private int iPosX;
    private int iPosY;
    private int speed;
    private boolean collision;

    public Balls(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.speed = 1;
        this.collision = false;
        this.width = 30;
        this.height = 30;
        this.game = game;
        this.width = width;
        this.height = height;
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

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    
    public int getiPosX() {
        return iPosX;
    }

    public void setiPosX(int iPosX) {
        this.iPosX = iPosX;
    }

    public int getiPosY() {
        return iPosY;
    }

    public void setiPosY(int iPosY) {
        this.iPosY = iPosY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    @Override
    public void tick() {
        
        //left margin
        if (getX() <= -10){
            setX(-10);
            setCollision(true);
        }
        //top margin
        else if( getY() + 100 >= game.getHeight()){
            setY(game.getHeight()- 100);
            setCollision(true);
        }
        //bottom margin
        else if (getY() <= -10){
            setY(-10);
            setCollision(true);
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player_FrozenMorty[4], getX(),getY(), getWidth(), getHeight(), null);
       
    }
    
    
}
