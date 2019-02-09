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
public abstract class Item {
    protected int x;            // to store x position
    protected int y;            // to store y position
    
    
    
    /**
     * Set initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y) {
     this.x = x;
     this.y = y;
    }
    /**
     * Set y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Set x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get y value
     * @return y
     */
    public int getY() {
        return y;
    }
/**
     * Get x value
     * @return x
     */
    public int getX() {
        return x;
    }
    
    /**
     * To update position of the item for every trick
     */
    public abstract void tick();
    
    /**
     * To paint the item
     * @param g <b> Graphics </b> object to paint the item
     */
    public abstract void render(Graphics g);
    
}
