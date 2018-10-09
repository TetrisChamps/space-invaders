package spaceinv.model.ships;


import java.util.List;
import java.util.Random;

import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
    Handle movement of all ships (one at the time)
 */
public class ShipFormation {

    public static final double PADDING_LEFT = 100;
    public static final double PADDING_RIGHT = GAME_WIDTH - PADDING_LEFT;

    private static final Random rand = new Random();
    private final List<AbstractSpaceShip> ships;
    private int indexToMove;

    public int destroyShip(){return 0;}
    // TODO destroy a ship return a score

    public ShipFormation(List<AbstractSpaceShip> ships) {
        this.ships = ships;
        indexToMove = ships.size() - 1;
    }

    // TODO move all ships on the x axis. If they cant move on x, move down and change direction.

    // TODO Some method to move the ships
    public boolean move(double deltaTime) {
        for (AbstractSpaceShip ship : ships) {

        }
        return false;
    }

    // TODO Some method to remäve ship hit by rocket

    private boolean isAnyShipOutOfBounds() {
        for (AbstractSpaceShip ship : this.ships) {
            if (!ship.checkBoundaries()) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return ships.size();
    }


    public List<AbstractSpaceShip> getShips() {
        return ships;
    }
}
