package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;

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
        this.speed = 1;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean intersecta(Player obj) {

        return getPerimetro().intersects(obj.getPerimetro());
    }

    public void reboot() {
        setCollision(false);

    }

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
        else if (getY() <= -10) {
            setY(-10);
            setCollision(true);
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.lives, getX(), getY(), getWidth(), getHeight(), null);
    }

}
