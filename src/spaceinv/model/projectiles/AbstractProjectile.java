package spaceinv.model.projectiles;
import spaceinv.model.AbstractMovable;

public abstract class AbstractProjectile extends AbstractMovable {
    private Direction travelDirection;

    public AbstractProjectile(double x, double y, double width, double height, double movementSpeed, Direction travelDirection) {
        super(x, y, width, height, movementSpeed);
        this.travelDirection = travelDirection;
    }

    @Override
    public void move(double deltaTime, Direction direction) {
        if (direction == Direction.UP) {
            this.setY(this.getY() - this.getMovementSpeed() * deltaTime);
        }
        else if (direction == Direction.DOWN) {
            this.setY(this.getY() + this.getMovementSpeed() * deltaTime);
        }
    }

    public void move(double deltaTime) {
        this.move(deltaTime, travelDirection);
    }
}
