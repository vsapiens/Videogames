package video.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

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
    private KeyManager keyManager;      // to manage the keyboard
    private LinkedList<Ball> balls;     // to manage the falling objects
    private boolean gameover;           // to manage the lives of the player
    private int fallen;                 // to manage the lives of the player

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
                tick();
                render();
                delta--;
            }
        }
        stop();
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
        balls = new LinkedList<Ball>();
        this.gameover = false;
        this.fallen = 0;
    }
    /**
     * To get the title of the game
     * @return title
     */
    public String getTitle() {
        return title;
    }
/**
 * To get the state of the game
 * @return 
 */
    public boolean isGameover() {
        return gameover;
    }
    /**
     * To set the state of the game
     * @param gameover 
     */
    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }
    /**
     * To set the title of the game
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
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
     * To get the fallen objects if the game
     * @return fallen
     */
    public int getFallen() {
        return fallen;
    }
    /**
     * To set the fallen 
     * @param fallen 
     */
    public void setFallen(int fallen) {
        this.fallen = fallen;
    }
    /**
     * initializing the display window of the game
     */
    private void init() {
        int iPosX, iPosY;
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth()/2, getHeight() - 100, 100, 100, this);

        for (int i = 0; i <= (int)(Math.random() * ((6 - 3) + 1)) + 6; i++) {
            iPosX = (int)(Math.random() * ((getWidth()) + 1));
            iPosY = (int)(Math.random() * ((-100 +30) + 1)) -30;
            balls.add(new Ball( iPosX, iPosY, 50, 50, this));
        }
        
        display.getJFrame().addKeyListener(keyManager);
    }

    /**
     * To get the key manager of the game
     *
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * set of steps
     */

    private void tick() {
        
        //if player has no more lives it sets gameover to true
        
        if(player.getLives() == 0)
        {
         setGameover(true);   
        }
        
        if (!isGameover()) {
            player.tick();
            keyManager.tick();
            // avancing player with collision
            for (int i = 0; i < balls.size(); i++) {
                
                //sets the speed depending of the lives
                balls.get(i).setSpeed((int)(Math.random() * ((8-player.getLives() - 1) + 1)) + 1);
                
                balls.get(i).tick();          
                // if a player intersects the ball under the object it reboots the location and 
                if(player.intersecta(balls.get(i)) && player.getPrevY() > balls.get(i).getY())
                {
                   balls.get(i).reboot(); 
                   Assets.squeeze.play();
                   player.setScore(player.getScore()+100);
                }
                
                //if the balls colides decreases scores in 20 and sets collision to false
                if(balls.get(i).isCollision())
                {
                   
                   balls.get(i).setCollision(false);
                   
                   Assets.bomb.play();
                   
                   player.setScore(player.getScore()-20);
                   
                   setFallen(getFallen()+1);
                   
                   //if 10 obejects have fallen it takes out one live
                  if(getFallen() == 10)
                  {
                    player.setLives(player.getLives()-1); 
                    setFallen(0);
                  }
                }
            }
        }
        //sets the previous y coordinate to the one in this tick
        player.setPrevY(player.getY());
    }

    /**
     * to render the game thread
     */

    private void render() {
        //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of the game,
        if not null, then we display every image of the game but after clearing the assets, 
        getting the graphic object from the buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            //to set the background
            g.drawImage(Assets.background, 0, 0, width, height, null);
            
            //to determine the font of the score
            g.setColor(Color.white);
            g.setFont(new Font("Serif", Font.BOLD, 20));
            
            //renders player and lives
            if(!isGameover())
            {
              player.render(g);
            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).render(g);
            }
        
            for (int i = 1; i <= player.getLives(); i++) {
                g.drawImage(Assets.lives, 30 * i, 10, 25, 25, null);
            }
            g.drawString( " " + player.getScore() , 20, 50);
            }
            //render the gameover picture and show the score
            else{
             
              g.drawImage(Assets.gameover, 0, 0, width, height, null);
              g.drawString( "Score: " + player.getScore() , getWidth(), getHeight());
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

    /**
     * stops the thread of the game
     */
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
