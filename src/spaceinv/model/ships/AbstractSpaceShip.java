package spaceinv.model.ships;

import spaceinv.model.AbstractMovable;

import java.util.Random;


/*
 * An alien attacker for the space invaders game
 *
 * Base class for all ships
 */

public abstract class AbstractSpaceShip extends AbstractMovable {

    public static final double SHIP_WIDTH = 50;
    public static final double SHIP_HEIGHT = 50;
    public static final double SHIPS_DX = 5;
    public static final double SHIPS_DY = 7;
    public static final double BASE_MOVEMENT_SPEED = 35;
    private static Random rand = new Random();   // TODO

    private double minX;  // min and max for ship to move i x-dir
    private double maxX;

    private final int points;

    public AbstractSpaceShip(double x, double y, int points) {
        super(x, y, SHIP_WIDTH, SHIP_HEIGHT, BASE_MOVEMENT_SPEED);
        this.setMovingDirection(Direction.LEFT);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    // To be overridden
    public abstract Object copyOf();

    // For ships moving back and forth
    public void setMoveInterval(double minX, double maxX) {
        // TODO
    }

    @Override
    public void move(double deltaTime, Direction direction) {
        // TODO: move move logic here
    }
}
