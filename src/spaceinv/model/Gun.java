package spaceinv.model;


import spaceinv.model.projectiles.Rocket;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
 * A Gun for the SpaceInv game
 */
public class Gun implements IPositionable {

    public static final double MAX_SPEED = 2;

    private double x;
    private double y;
    private double size;
    private double moveSpeed;

    public Gun(double x, double y, double size, double moveSpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.moveSpeed = moveSpeed;
    }


    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return size;
    }

    @Override
    public double getHeight() {
        return size;
    }

    public double getMoveSpeed() { return moveSpeed; }

    public void setX(double x) { this.x = x; }
}
