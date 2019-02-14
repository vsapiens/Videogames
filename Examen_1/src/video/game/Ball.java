package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Thread.sleep;

/**
 *
 * @author ErickFrank
 */
public class Ball extends Item {

    private final Game game;
    private int width;
    private int height;
    private int speed;
    private boolean collision;

    public Ball(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.width = width;
        this.height = height;
        this.speed = (int)(Math.random() * ((2 - 1) + 1)) + 1;
        this.collision = false;
    }

    /**
     * To get the width of the instance of the game
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To set the width of the instance of the game
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * To get the height of the game
     * @return height 
     */
    public int getHeight() {
        return height;
    }
    /**
     * To set the height of the 
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * To get the collision of the ball
     * @return collision
     */
    public boolean isCollision() {
        return collision;
    }
    /**
     * To set the collision of the ball
     * @param collision 
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    /**
     * To get the speed of the ball
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * To set the speed of the ball
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /**
     * To get the perimeter of the rectangle inside the images
     * @return Rectangle
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    /**
     * To get the value of intersection another object
     * @param obj
     * @return intersects
     */
    public boolean intersecta(Player obj) {

        return getPerimetro().intersects(obj.getPerimetro());
    }
    /**
     * To set the random positions of the balls
     */
    public void reboot() {
           setX((int)(Math.random() * ((game.getWidth()) + 1)));
           setY((int)(Math.random() * ((-100 +30) + 1)) -30);
    }
    /**
     * To tick the ball in the game
     */
    @Override
    public void tick() {

        //to simulate the fall from the top
        setY(getY() + getSpeed());

        //right margin
        if (getX() + 10 >= game.getWidth()) {
            setX(game.getWidth() - 10);
        } //left margin
        else if (getX() <= -10) {
            setX(-10);
        } //bottom margin
        else if (getY() + 10 >= game.getHeight()) {
            //if touches the bottom margin it sets collision to true and reboots position
            setCollision(true);
            reboot();
        }
    }
    /**
     * Renders the ball
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.lives, getX(), getY(), getWidth(), getHeight(), null);
    }

}
