package spaceinv.model.projectiles;


import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.AbstractMovable;
import spaceinv.model.SpaceInv;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.ShipFormation;

// Had it's initial position in the gun but has now started moving away from the gun
public class Rocket extends AbstractProjectile {
    public Rocket(double x, double y, double movementSpeed) {
        super(x, y, 10, 20, movementSpeed, Direction.UP);
    }

    public void update(double deltaTime, ShipFormation formation) {
        this.move(deltaTime);
        if (!SpaceInv.PLAY_AREA.intersects(this)) {
            EventService.add(new Event(Event.Type.ROCKET_OUT_OF_BOUNDS));
            return;
        }
        for (AbstractSpaceShip ship : formation.getShips()) {
            if (ship.intersects(this)) {
                EventService.add(new Event(Event.Type.ROCKET_HIT_SHIP, ship));
                return;
            }
        }

    }
}
