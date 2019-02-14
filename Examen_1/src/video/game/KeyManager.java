/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ErickFrank
 */
public class KeyManager implements KeyListener {

    public boolean up;          // flag to move up the player
    public boolean down;        // flag to move down the player
    public boolean left;        // flag to move left the player
    public boolean right;       // flag to move right the player

    private boolean keys[];     // to store all the flags for every key

    /**
     * To set the left button to pressed
     *
     * @param pressed
     */
    public void setLeft(boolean pressed) {
        keys[KeyEvent.VK_LEFT] = pressed;
    }

    /**
     * To set the right button to pressed
     *
     * @param pressed
     */
    public void setRight(boolean pressed) {
        keys[KeyEvent.VK_RIGHT] = pressed;
    }

    /**
     * To set the down button to pressed
     *
     * @param pressed
     */
    public void setDown(boolean pressed) {
        keys[KeyEvent.VK_DOWN] = pressed;
    }

    /**
     * To set the up button to pressed
     *
     * @param pressed
     */
    public void setUp(boolean pressed) {
        keys[KeyEvent.VK_UP] = pressed;
    }

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    /**
     * Sets to true the key manager
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key released
        keys[e.getKeyCode()] = true;
    }
    /**
     * Sets to false the key manager
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
    }

    /**
     * to enable or disable moves on every tick
     */

    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }
}
