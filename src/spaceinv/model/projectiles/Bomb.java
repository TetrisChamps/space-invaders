package spaceinv.model.projectiles;


import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.Gun;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.statics.Ground;

// Dropped by the ships
public class Bomb extends AbstractProjectile {
    // Can be public
    private static final double BOMB_WIDTH = 10;
    private static final double BOMB_HEIGHT = 10;


    public Bomb(double x, double y, double movementSpeed) {
        super(x, y, BOMB_WIDTH, BOMB_HEIGHT, movementSpeed, Direction.DOWN);
    }

    public void update(double deltaTime, Ground ground, Gun gun) {
        this.move(deltaTime);
        if (ground.intersects(this)) {
            EventService.add(new Event(Event.Type.BOMB_HIT_GROUND));
            return;
        }
        if (gun.intersects(this)) {
            EventService.add(new Event(Event.Type.BOMB_HIT_GUN, this));
        }


    }
}
