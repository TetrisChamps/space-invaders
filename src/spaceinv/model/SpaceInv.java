package spaceinv.model;

import spaceinv.model.levels.ILevel;
import spaceinv.model.projectiles.Bomb;
import spaceinv.model.projectiles.Rocket;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.view.Assets;

import java.util.ArrayList;
import java.util.List;

/*
 * Logic for the SpaceInv Game
 * Model class representing the "overall" game logic
 *
 * Nothing visual here
 *
 * See:
 * - week6/samples/catchtherain
 */
public class SpaceInv {

    public static final double GAME_WIDTH = 800;
    public static final double GAME_HEIGHT = 400;

    public static final long ONE_SEC = 1_000_000_000;
    public static final long HALF_SEC = 500_000_000;
    public static final long TENTH_SEC = 100_000_000;

    public static final Rect PLAY_AREA = new Rect(0, 0, GAME_WIDTH, GAME_HEIGHT);

    // TODO
    //private final List<AbstractSpaceShip> ships;

    private final Ground ground;           // Border for bombs
    //private final OuterSpace outerSpace;   // Border for rocket
    private final Gun gun;
    private final ShipFormation formation;

    private Rocket rocket;
    private int points;

    private List<Bomb> bombs;

    // Timing. All timing handled here!
    private long timeForLastMove;
    private long timeForlastFire;
    private long shipMoveDelay = TENTH_SEC;

    public SpaceInv(ILevel level) {
        this.ground = level.getGround();
        //this.outerSpace = level.getOuterSpace();
        this.gun = level.getGun();
        this.formation = level.getFormation();
        this.bombs = new ArrayList<>();
    }

    // ------ Game loop (called by timer) -----------------
    private long lastUpdate = 0;

    public void update(long now) {
        // Calculate time since last frame (seconds)
        double deltaTime = 0;
        if (this.lastUpdate != 0) {
            deltaTime = (double) (now - this.lastUpdate) / ONE_SEC;
        }
        this.lastUpdate = now;
        // Update components
        if (this.rocket != null) {
            this.rocket.update(deltaTime, this.formation, this.bombs);
        }
        for (Bomb bomb : bombs) {
            bomb.update(deltaTime, ground, gun);
        }
        this.gun.update(deltaTime);
        this.formation.update(deltaTime, gun, ground);
    }

    // ------------- Increase pressure on player
    private boolean finalSpeed = false;
    private boolean incSpeed = false;

    // ---------- Interaction with GUI  -------------------------

    public void fireGun() {
        if (rocket == null) {
            rocket = gun.shootGun();
            Assets.INSTANCE.rocketLaunched.play();
        }
    }

    public void setGunMovingDirection(AbstractMovable.Direction direction) {
        this.gun.setMovingDirection(direction);
    }

    //TODO move to absMovable
    public void move(Gun ipos /* Should be AbsMovable */, int dirX, int dirY, double moveSpeed) {
        double movementX = ipos.getX() + moveSpeed * dirX;
        double movementY = ipos.getY() + moveSpeed * dirY;
        if (checkBoundaries(ipos, movementX, movementY)) {
            ipos.setX(movementX);
            //ipos.setY(movementY); //TODO uncomment when absMovable is implemented.
        }
    }

    //--------- Ship -----------
    public void shipHit(AbstractSpaceShip ship) {
        points += formation.destroyShip(ship);
        removeRocket();
    }

    //--------- Rocket ---------
    public void removeRocket() {
        rocket = null;
    }

    public void rocketHitBomb(Bomb bomb){
        rocket = null;
        bombs.remove(bomb);
    }

    //--------- Bomb -----------
    public void dropBomb(Bomb bomb) {
        if (bombs.size() < 2) {
            bombs.add(bomb);
        }
    }

    public void removeBomb(Bomb bomb) {
        bombs.remove(bomb);
    }


    public boolean checkBoundaries(IPositionable ipos, double X, double Y) {

        return (X >= 0 && X <= (GAME_WIDTH - ipos.getWidth())) &&
                (Y >= 0 && Y <= (GAME_HEIGHT - ipos.getHeight()));
    }

    // --------- Send everything to be rendered --------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> ps = new ArrayList<>();
        // TODO Add all to be rendered

        ps.add(this.ground);
        for (AbstractSpaceShip ship : formation.getShips()) {
            ps.add(ship);
        }
        for (Bomb bomb : bombs) {
            ps.add(bomb);
        }
        ps.add(this.gun);
        if (rocket != null) {
            ps.add(rocket);
        }
        return ps;
    }

    public int getPoints() {
        return points;
    }


}
