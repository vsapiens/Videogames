/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author ErickFrank
 */
public class Game implements Runnable{

    private BufferStrategy bs;          // to have several buffers when displayinh
    private Graphics g;                 // to paint objects
    private Display display;            // to display in the game
    String title;                       // title of the window
    private int width;                  // width of the window
    private int height;                 // height of the window
    private Thread thread;              // thread to create the game
    private boolean running;            // to set the game
    private Player player;              // to use a player
    private KeyManager keyManager;      // to manage the keyboard
    private LinkedList<Balls> balls;    // to manage the falling objects
    private boolean gameover;           // to manage the lives of the player

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
        while(running){
            //setting the time now to the actual time
            now = System.nanoTime();
            //accumulating delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            //updating the last time
            lastTime = now;
            
            //if delta is positive we tick the game
            if(delta >= 1 ){
            tick();
            render();
            delta --;
            }
        }
        stop();
       }
    
    /**
     * to create title, width, and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game (String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        balls = new LinkedList<Balls>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
     * initializing the display window of the game
     */
    private void init()
    {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(0, getHeight()-100, 100, 100, this);
        
        for(int i = 0; i < (int)(Math.random()*6)+4; i++)   
        {
            balls.add(new Balls(getWidth(), getHeight()-100, 25, 25, this));
        }
        display.getJFrame().addKeyListener(keyManager);
    }
    
    /**
     * To get the key manager of the game 
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    /**
     * set of steps 
     */
    
    private void tick(){

        keyManager.tick();
        // avancing player with collision
        player.tick();
        for(int i = 0; i < balls.size(); i++)   
        {
            balls.get(i).tick();
        }
        
    }
          /**
           * to render the game thread
           */
    
    private void render()
    {
     //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of the game,
        if not null, then we display every image of the game but after clearing the assets, 
        getting the graphic object from the buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0 , width, height, null);
            for(int i = 0; i < balls.size(); i++)   
            {
                balls.get(i).render(g);
            }
            player.render(g);
            bs.show();
            g.dispose();
        }
    }
    
    /**
     * setting the thread for the game
    */
    public synchronized void start(){
     if(!running){
        running = true;
        thread = new Thread(this);
        thread.start();
            }
    }
    
    /**
     * stops the thread of the game
     */
    public synchronized void stop(){
     if(running){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }  
      }
    }
    
}
