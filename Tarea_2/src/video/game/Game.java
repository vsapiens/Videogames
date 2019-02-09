/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import static java.lang.Math.random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ErickFrank
 */
public class Game implements Runnable {

    private BufferStrategy bs;          // to have several buffers when displayinh
    private Graphics g;                 // to paint objects
    private Display display;            // to display in the game
    String title;                       // title of the window
    private int width;                  // width of the window
    private int height;                 // height of the window
    private Thread thread;              // thread to create the game
    private boolean running;            // to set the game
    private Player player;              // to use a player
    private Enemy enemy;                // to manage the enemy
    private KeyManager keyManager;      // to manage the keyboard
    private MouseManager mouseManager;  // to manage the mouse
    private boolean gameover;

    private SoundClip bomb;             // to manage the soundclip of the collision             // to manage the lives 

    @Override
    public void run() {
        init();
        //frames per second
        int fps = 50;
        //time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        //initializing delta
        double delta = 0;
        //define now to use inside the loop
        long now;
        //initializing the last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            //setting the time now to the actual time
            now = System.nanoTime();
            //accumulating delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            //updating the last time
            lastTime = now;

            //if delta is positive we tick the game
            if (delta >= 1) {

                try {
                    tick();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                render();
                delta--;
            }

        }
        stop();
    }

    public boolean isGameOver() {
        return gameover;
    }

    public void setGameOver(boolean gameOver) {
        this.gameover = gameOver;
    }

    /**
     * to create title, width, and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        this.gameover = false;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        player = new Player( (int) (random() * ( 2 * width / 3) +700), (int) (random() * ( height / 6) + 100), 100, 100, this);
        enemy = new Enemy( (int) ( random() * ( 3 * width / 5 )), (int) (random() *  ( height )), 50, 50, this);
        display.getJFrame().addKeyListener(keyManager);
        display.getJFrame().addMouseListener(mouseManager);
        display.getJFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
    }

    /**
     * Returns the KeyManager
     *
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * Returns the MouseManager
     *
     * @return mouseManager
     */
    public MouseManager getMouseManager() {
        return mouseManager;
    }

    private void tick() throws InterruptedException {

        if (player.getPerimetro().contains(getMouseManager().getX(), getMouseManager().getY())) {
            getMouseManager().setDragged(true);
        } else {
            getMouseManager().setDragged(false);
        }
        // manages the 
        keyManager.tick();

        // avancing player and enemy with collision
        player.tick();
        enemy.tick();
        enemy.follow(player.getX(),player.getY());

        if (player.intersecta(enemy) && !isGameOver()) {

            // substracts one live from player and reboots position of player and enemy
            player.reboot();
            enemy.reboot();
            
            thread.sleep(2000);
            
            if (player.getLives() == 0) {
                setGameOver(true);
            }
            

        }

    }

    private void render() {
        //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of the game,
        if not null, then we display every image of the game but after clearing the Rectanlge, 
        getting the graphic object from the buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);

            if (!isGameOver()) {

                for (int i = 1; i <= player.getLives(); i++) {
                    g.drawImage(Assets.lives, 30 * i, 10, 25, 25, null);
                }

                player.render(g);
                enemy.render(g);
            } else {

                g.drawImage(Assets.gameover, 0, 0, width, height, null);

            }

            bs.show();
            g.dispose();
        }
    }

    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
