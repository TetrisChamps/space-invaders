package spaceinv.model.ships;


import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.AbstractMovable.Direction;
import spaceinv.model.Gun;
import spaceinv.model.SpaceInv;
import spaceinv.model.statics.Ground;

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

    public int destroyShip(AbstractSpaceShip ship) {
        ships.remove(ship);
        if (ships.isEmpty()) {
            EventService.add(new Event(Event.Type.GAME_OVER));
        }
        return ship.getPoints();
    }
    // TODO destroy a ship return a score

    public ShipFormation(List<AbstractSpaceShip> ships) {
        this.ships = ships;
        indexToMove = ships.size() - 1;
    }

    public void update(double deltaTime, Gun gun, Ground ground) {
        int verticalOffset = 20;
        this.dropBombs();
        this.move(deltaTime);
        if (isAnyShipOutOfBounds()) {
            double outOfBoundsOnLeft = SpaceInv.PLAY_AREA.getX();
            double outOfBoundsOnRight = SpaceInv.PLAY_AREA.getX() + SpaceInv.PLAY_AREA.getWidth();
            for (AbstractSpaceShip ship : this.ships) {
                if (ship.getX() < outOfBoundsOnLeft) {
                    outOfBoundsOnLeft = ship.getX();
                }
                if (ship.getX() + ship.getWidth() > outOfBoundsOnRight) {
                    outOfBoundsOnRight = ship.getX() + ship.getWidth();
                }
            }
            outOfBoundsOnLeft = Math.abs(outOfBoundsOnLeft + SpaceInv.PLAY_AREA.getX());
            outOfBoundsOnRight = Math.abs(outOfBoundsOnRight - SpaceInv.PLAY_AREA.getX() - SpaceInv.PLAY_AREA.getWidth());
            for (AbstractSpaceShip ship : this.ships) {
                if (ship.getMovingDirection() == Direction.LEFT) {

                    ship.setX(ship.getX() + 2 * outOfBoundsOnLeft);
                    ship.setMovingDirection(Direction.RIGHT);
                } else if (ship.getMovingDirection() == Direction.RIGHT) {
                    ship.setX(ship.getX() - 2 * outOfBoundsOnRight);
                    ship.setMovingDirection(Direction.LEFT);
                }
                ship.setY(ship.getY() + verticalOffset);
            }
        }
        for (AbstractSpaceShip ship : this.ships) {
            if (!SpaceInv.PLAY_AREA.contains(ship) || ship.intersects(ground) || ship.intersects(gun)) {
                System.out.println("GAME OVER");
                EventService.add(new Event(Event.Type.GAME_OVER));
            }
        }
    }

    // TODO move all ships on the x axis. If they cant move on x, move down and change direction.

    // TODO Some method to move the ships
    public void move(double deltaTime) {
        for (AbstractSpaceShip ship : this.ships) {
            if (ship.getMovingDirection() == Direction.LEFT) {
                ship.setX(ship.getX() - ship.getMovementSpeed() * deltaTime);
            } else if (ship.getMovingDirection() == Direction.RIGHT) {
                ship.setX(ship.getX() + ship.getMovementSpeed() * deltaTime);

            }
        }
    }

    public void dropBombs() {
        for(AbstractSpaceShip ship : this.ships){
            if(ship instanceof Bomber){
                ((Bomber)ship).dropBomb();
            }
        }
    }


    // TODO Some method to remäve ship hit by rocket

    private boolean isAnyShipOutOfBounds() {
        for (AbstractSpaceShip ship : this.ships) {
            if (!SpaceInv.PLAY_AREA.contains(ship)) {
                SpaceInv.PLAY_AREA.contains(ship);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return ships.size();
    }


    public List<AbstractSpaceShip> getShips() {
        return ships;
    }
}
