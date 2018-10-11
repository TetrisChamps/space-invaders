package spaceinv.model;

public abstract class AbstractMovable extends AbstractPositionable {

    public enum Direction {
        LEFT,
        RIGHT,
        DOWN,
        UP,
        NONE
    }

    private double movementSpeed;
    private Direction movingDirection = Direction.NONE;

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public AbstractMovable(double x, double y, double width, double height, double movementSpeed) {
        super(x, y, width, height);
        this.movementSpeed = movementSpeed;
    }

    public void reverseHorizontalDirection() {
        if (this.movingDirection == Direction.LEFT) {
            this.movingDirection = Direction.RIGHT;
        } else if (this.movingDirection == Direction.RIGHT) {
            this.movingDirection = Direction.LEFT;
        }
    }

    public void setMovingDirection(Direction direction) {
        this.movingDirection = direction;
    }

    public Direction getMovingDirection() {
        return this.movingDirection;
    }

    public abstract void move(double deltaTime, Direction direction);
}
