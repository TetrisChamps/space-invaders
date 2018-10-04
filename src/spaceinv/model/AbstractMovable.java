package spaceinv.model;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

public abstract class AbstractMovable extends AbstractPositionable{
    private double movementSpeed;
    public AbstractMovable(double x, double y, double width, double height, double movementSpeed) {
        super(x, y, width, height);
        this.movementSpeed = movementSpeed;
    }



    public void move(int dirX, int dirY, double moveSpeed){
        double movementX = this.getX() + moveSpeed * dirX;
        double movementY = this.getY() + moveSpeed * dirY;
        if(checkBoundaries(movementX, movementY)){
            this.setX((int)movementX);
            this.setY((int)movementY);
        }
    }

    public boolean checkBoundaries(double X, double Y){
        return  (X >= 0 && X <= (GAME_WIDTH - this.getWidth())) &&
                (Y >= 0 && Y <= (GAME_HEIGHT - this.getHeight()));
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
