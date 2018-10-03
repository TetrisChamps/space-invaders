package spaceinv.model;


import spaceinv.model.projectiles.Rocket;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
 * A Gun for the SpaceInv game
 */
public class Gun implements IPositionable {

    public static final double MAX_SPEED = 2;

    private int x;
    private int y;
    private int size;

    public Gun(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
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
}
