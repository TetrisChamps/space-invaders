package spaceinv.model.ships;

import spaceinv.model.AbstractMovable;
import spaceinv.model.IPositionable;

import java.util.Random;


/*
 * An alien attacker for the space invaders game
 *
 * Base class for all ships
 */

public abstract class AbstractSpaceShip extends AbstractMovable {


    public AbstractSpaceShip(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public static final double SHIP_WIDTH = 40;
    public static final double SHIP_HEIGHT = 30;
    public static final double SHIPS_DX = 5;
    public static final double SHIPS_DY = 7;

    private static Random rand = new Random();   // TODO

    private double minX;  // min and max for ship to move i x-dir
    private double maxX;

    private double x;
    private double y;

    // To be overridden
    public abstract int getPoints();

    // To be overridden
    public abstract Object copyOf();

    // For ships moving back and forth
    public void setMoveInterval(double minX, double maxX){
        // TODO
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double y) {
    this.y = y;
    }

    @Override
    public double getWidth() {
        return SHIP_WIDTH;
    }

    @Override
    public double getHeight() {
        return SHIP_HEIGHT;
    }
}
