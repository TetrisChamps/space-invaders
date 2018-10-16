package spaceinv.model.ships;

import spaceinv.model.AbstractMovable;

import java.util.Random;


/*
 * An alien attacker for the space invaders game
 *
 * Base class for all ships
 */

public abstract class AbstractSpaceShip extends AbstractMovable {
    
    public static final double SHIP_WIDTH = 40;
    public static final double SHIP_HEIGHT = 30;
    public static final double SHIPS_DX = 5;
    public static final double SHIPS_DY = 7;

    private static Random rand = new Random();   // TODO

    private double minX;  // min and max for ship to move i x-dir
    private double maxX;

    public AbstractSpaceShip(double x, double y, double movementSpeed) {
        super(x, y, SHIP_WIDTH, SHIP_HEIGHT, movementSpeed);
        this.setMovingDirection(Direction.LEFT);
    }

    // To be overridden
    public abstract int getPoints();

    // To be overridden
    public abstract Object copyOf();

    // For ships moving back and forth
    public void setMoveInterval(double minX, double maxX){
        // TODO
    }
}
