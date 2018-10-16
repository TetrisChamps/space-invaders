package spaceinv.model.ships;

/*
 *   Type of space ship
 */
public class Frigate extends AbstractSpaceShip {

    public Frigate() {
        super(0, 0, 20, 10);
    }

    @Override
    public Object copyOf() {
        return new BattleCruiser();
    }
}
