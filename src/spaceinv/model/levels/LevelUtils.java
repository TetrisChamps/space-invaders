package spaceinv.model.levels;

import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.ShipFormation;

import java.util.ArrayList;
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
        double xDistance = 0;
        for (AbstractSpaceShip s : ships) {
            s.setX(s.getX() + xDistance);
            s.setMoveInterval(s.getX(), s.getX() + 2 * s.getWidth());
            xDistance = xDistance + s.getWidth() + horizonDistBetweenShip;
        }
        return ships;
    }

    // To addAll many rows of ships into single list
    public static <T extends AbstractSpaceShip> List<T> addAll(List<T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<T> l : lists) {
            result.addAll(l);
        }
        return result;
    }
}
