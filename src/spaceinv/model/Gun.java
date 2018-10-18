package spaceinv.model;


import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.projectiles.Rocket;

/*
 * A Gun for the SpaceInv game
 */
public class Gun extends AbstractMovable {

    public void update(double deltaTime) {
        this.move(deltaTime, this.getMovingDirection());
        if (!SpaceInv.PLAY_AREA.contains(this)) {
            if (this.getMovingDirection() == Direction.LEFT) {
                this.setX(SpaceInv.PLAY_AREA.getX());
            } else if (this.getMovingDirection() == Direction.RIGHT) {
                this.setX(SpaceInv.PLAY_AREA.getX() + SpaceInv.PLAY_AREA.getWidth() - this.getWidth());
            }
        }
    }

    public Gun(double x, double y, double size, double movementSpeed) {
        super(x, y, size, size, movementSpeed);
    }

    public Rocket shootGun() {
        //TODO: EventService.add(new Event(Event.Type.ROCKET_LAUNCHED));
        return new Rocket(getX() + (getWidth() - 10) / 2, getY(), 300);
    }

    @Override
    public void move(double deltaTime, Direction direction) {
        if (direction == Direction.LEFT) {
            this.setX(this.getX() - this.getMovementSpeed() * deltaTime);
        } else if (direction == Direction.RIGHT) {
            this.setX(this.getX() + this.getMovementSpeed() * deltaTime);
        }
    }

}
