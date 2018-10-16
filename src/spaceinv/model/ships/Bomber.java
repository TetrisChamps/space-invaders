package spaceinv.model.ships;

import spaceinv.model.projectiles.Bomb;

/*
 *   Type of space ship
 */
public class Bomber extends AbstractSpaceShip {

    public Bomber() {
        super(0, 0, 20, 10);
    }

    @Override
    public Object copyOf() {
        return new BattleCruiser();
    }
}
