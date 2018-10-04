package spaceinv.model.ships;

/*
 *   Type of space ship
 */
public class BattleCruiser extends AbstractSpaceShip {

    private final int points = 10;

    public BattleCruiser() {
        super(0, 0, 50, 50, 1);
    }


    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public Object copyOf() {
        return new BattleCruiser();
    }
}
