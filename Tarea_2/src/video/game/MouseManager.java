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

    public MouseManager() {
        this.izquierdo = false;
        this.derecho = false;
        this.x = 0;
        this.y = 0;
    }

    public boolean isDragged() {
        return dragged;
    }

    public void setDragged(boolean dragged) {
        this.dragged = dragged;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1 && isDragged()) {
            setIzquierdo(true);
            setX(e.getX()-50);
            setY(e.getY()-50);
            setDragged(false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
            setX(e.getX());
            setY(e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && isDragged()) {
            setIzquierdo(true);
            setX(e.getX()-50);
            setY(e.getY()-50);
        }
    }

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

    public boolean isIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(boolean izquierdo) {
        this.izquierdo = izquierdo;
    }

    public boolean isDerecho() {
        return derecho;
    }

    public void setDerecho(boolean derecho) {
        this.derecho = derecho;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
