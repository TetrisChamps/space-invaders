package spaceinv.model.ships;

import spaceinv.model.projectiles.Bomb;

/*
 *   Type of space ship
 */
public class Bomber extends AbstractSpaceShip {

    public Bomber() {
        super(0, 0, 20, 20);
    }

    @Override
    public Object copyOf() {
        return new Bomber();
    }
}
