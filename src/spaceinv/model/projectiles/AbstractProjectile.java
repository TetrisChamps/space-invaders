package spaceinv.model.projectiles;
import spaceinv.model.AbstractMovable;

public abstract class AbstractProjectile extends AbstractMovable {
    private int travelDirection;

    public AbstractProjectile(double x, double y, double width, double height, double movementSpeed, int travelDirection) {
        super(x, y, width, height, movementSpeed);
        this.travelDirection = travelDirection;
    }

    public void move() {
        double movementY = this.getY() + getMovementSpeed() * travelDirection;
        this.setY((int)movementY);
    }

    public abstract boolean isOutside();

}
