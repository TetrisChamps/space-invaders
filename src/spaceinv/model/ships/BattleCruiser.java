package spaceinv.model.ships;

/*
 *   Type of space ship
 */
public class BattleCruiser extends AbstractSpaceShip {

    public BattleCruiser() {
        super(0, 0, 10);
    }

    @Override
    public Object copyOf() {
        return new BattleCruiser();
    }
}
