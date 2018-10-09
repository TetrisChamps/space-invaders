package spaceinv.model;


import spaceinv.model.projectiles.Rocket;

/*
 * A Gun for the SpaceInv game
 */
public class Gun extends AbstractMovable {


    public static final double MAX_SPEED = 2;



    public Gun(double x, double y, double size, double movementSpeed) {
        super(x, y, size, size, movementSpeed);
    }

    public Rocket shootGun() {
        return new Rocket(getX() + (getWidth() - 10) / 2, getY(), 3);
    }


}
