/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author ErickFrank
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean izquierdo;      // to check if left has been pushed
    private boolean derecho;        // to check if right has been pushed
    private boolean dragged;       
    private int x;
    private int y;
    /**
     * To initialize the mouse manager with the left and right button in false
     */
    public MouseManager() {
        this.izquierdo = false;
        this.derecho = false;
        this.x = 0;
        this.y = 0;
    }
    /**
     * Return is the mouse is dragged
     * @return dragged
     */
    public boolean isDragged() {
        return dragged;
    }
    /**
     * To set the value of dragged 
     * @param dragged 
     */
    public void setDragged(boolean dragged) {
        this.dragged = dragged;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    /**
     * To set the position of the mouse to the image
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1 && isDragged()) {
            setIzquierdo(true);
            setX(e.getX()-50);
            setY(e.getY()-50);
            setDragged(false);
        }
    }
    /**
     * To set the x and y if the mouse is moved
     * @param e 
     */
    @Override
    public void mouseMoved(MouseEvent e) {
            setX(e.getX());
            setY(e.getY());
    }
    /**
     * To get the position if the mouse is clicked
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && isDragged()) {
            setIzquierdo(true);
            setX(e.getX()-50);
            setY(e.getY()-50);
        }
    }
    /**
     * To get the position where the mouse is pressed
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            setIzquierdo(false);
            setX(e.getX()-50);
            setY(e.getY()-50);
            setDragged(false);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * To change the left button
     * @return izquierdo
     */
    public boolean isIzquierdo() {
        return izquierdo;
    }
    /**
     * To get the status of the left button
     * @param izquierdo 
     */
    public void setIzquierdo(boolean izquierdo) {
        this.izquierdo = izquierdo;
    }
    /**
     * To change the right button
     * @return derecho
     */
    public boolean isDerecho() {
        return derecho;
    }
    /**
     * To get the status of the right button
     * @param derecho 
     */
    public void setDerecho(boolean derecho) {
        this.derecho = derecho;
    }
    /**
     * To get the x position of the mouse
     * @return x
     */
    public int getX() {
        return x;
    }
    /**
     * To set the x position of the mouse
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * To get the y position of the mouse
     * @return 
     */
    public int getY() {
        return y;
    }
    /**
     * To set the y position of the mouse
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }

}
