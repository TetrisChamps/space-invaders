package spaceinv.model.levels;

import spaceinv.model.SpaceInv;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.ShipFormation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*

        Utilities to create rows of ships and more

        *** Nothing to do here ***

 */
public final class LevelUtils {


    // Create a row of nShips as copies of prototype (which must be some subtype to AbstractSpaceShip)
    public static <T extends AbstractSpaceShip> List<T> asList(AbstractSpaceShip prototype, int nShips) {
        List<T> tmp = new ArrayList<>();
        for (int i = 0; i < nShips; i++) {
            T s = (T) prototype.copyOf();
            tmp.add(s);
        }
        return tmp;
    }

    // Add space between ships and set initial interval for movement in horizontal direction
    public static <T extends AbstractSpaceShip> List<T> distribute(List<T> ships, double horizonDistBetweenShip) {
        int shipsPerRow = (int)(SpaceInv.GAME_WIDTH - ShipFormation.PADDING_LEFT * 2) / (int)(AbstractSpaceShip.SHIP_WIDTH + horizonDistBetweenShip);
        int shipCount = ships.size();
        int startColumn = (shipsPerRow - Math.min(shipCount, shipsPerRow)) / 2;
        int i = 0;
        for (AbstractSpaceShip ship : ships) {
            if (i < Math.min(shipCount, shipsPerRow)) {
                ship.setX(SpaceInv.PLAY_AREA.getX() + ShipFormation.PADDING_LEFT + horizonDistBetweenShip / 2 + (startColumn + i) * (ship.getWidth() + horizonDistBetweenShip));
            }
            i++;
        }
        ships = ships.subList(0, Math.min(shipCount, shipsPerRow));
        return ships;
    }

    // To addAll many rows of ships into single list
    public static <T extends AbstractSpaceShip> List<T> addAll(List<T>... lists) {
        List<T> result = new ArrayList<>();
        int row = 0;
        for (List<T> l : lists) {
            for (AbstractSpaceShip ship : l) {
                ship.setY(row * (ship.getHeight() + 5));
            }
            result.addAll(l);
            row++;
        }
        return result;
    }
}
