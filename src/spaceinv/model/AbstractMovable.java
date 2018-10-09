package spaceinv.model;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

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

    public void setMovingDirection(Direction direction) {
        this.movingDirection = direction;
    }

    public void move(Direction direction, double deltaTime) {
        double oldX = this.getX();
        double oldY = this.getY();

        switch (direction) {
            case UP:
                this.setY(this.getY() - this.movementSpeed * deltaTime);
                break;
            case RIGHT:
                this.setX(this.getX() + this.movementSpeed * deltaTime);
                break;
            case DOWN:
                this.setY(this.getY() + this.movementSpeed * deltaTime);
                break;
            case LEFT:
                this.setX(this.getX() - this.movementSpeed * deltaTime);
                break;
            case NONE:
                return;
        }

        if (!checkBoundaries()) {
            this.setX(oldX);
            this.setY(oldY);
        }
    }

    public boolean checkBoundaries() {
        return (this.getX() >= 0 && this.getX() <= (GAME_WIDTH - this.getWidth())) &&
                (this.getY() >= 0 && this.getY() <= (GAME_HEIGHT - this.getHeight()));
    }

    public void move(double deltaTime) {
        move(this.movingDirection, deltaTime);
    }
}
