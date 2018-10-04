package spaceinv.model.projectiles;


import spaceinv.model.AbstractMovable;

// Had it's initial position in the gun but has now started moving away from the gun
public class Rocket extends AbstractProjectile {
    public Rocket(double x, double y, double movementSpeed) {
        super(x, y, 10, 20, movementSpeed, -1);
    }

    @Override
    public boolean isOutside() {
        return getY() + getHeight() <= 0;
    }
}
