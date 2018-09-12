package spaceinv.model.ships;

import java.util.Random;


/*
 * An alien attacker for the space invaders game
 *
 * Base class for all ships
 */

public abstract class AbstractSpaceShip  {

    public static final double SHIP_WIDTH = 40;
    public static final double SHIP_HEIGHT = 30;
    public static final double SHIPS_DX = 5;
    public static final double SHIPS_DY = 7;

    private static Random rand = new Random();   // TODO

    private double minX;  // min and max for ship to move i x-dir
    private double maxX;



    // To be overridden
    public abstract int getPoints();

    // To be overridden
    public abstract Object copyOf();

    public double getX() {
        return 0 ; // TODO
    }
    public void setX(double x) {
         // TODO
    }

    public double getWidth() {
        return 0;   // TODO
    }

    // For ships moving back and forth
    public void setMoveInterval(double minX, double maxX){
        // TODO
    }
}
