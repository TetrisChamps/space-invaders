package spaceinv.model;

import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.levels.ILevel;
import spaceinv.model.projectiles.Rocket;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.model.statics.OuterSpace;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Platform.exit;
import static spaceinv.model.Gun.MAX_SPEED;

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

    // TODO
    //private final List<AbstractSpaceShip> ships;

    private final Ground ground;           // Border for bombs
    private final OuterSpace outerSpace;   // Border for rocket
    private final Gun gun;
    private final ShipFormation formation;

    private Rocket rocket;
    private int points;

    // Timing. All timing handled here!
    private long timeForLastMove;
    private long timeForlastFire;
    private long shipMoveDelay = TENTH_SEC;

    public SpaceInv(ILevel level) {
        this.ground = level.getGround();
        this.outerSpace = level.getOuterSpace();
        this.gun = level.getGun();
        this.formation = level.getFormation();
    }

    // ------ Game loop (called by timer) -----------------
    private long lastUpdate = 0;
    public void update(long now) {
        double deltaTime = 0;
        if(lastUpdate != 0){
            deltaTime = (double)(now - lastUpdate) / ONE_SEC;
        }
        lastUpdate = now;
        if(rocket != null){
            rocket.move();
            if(rocket.isOutside()){
                rocket = null;
            }
        }
        gun.move(deltaTime);
        this.formation.move();
    }

    // ------------- Increase pressure on player
    private boolean finalSpeed = false;
    private boolean incSpeed = false;

    // ---------- Interaction with GUI  -------------------------

    public void fireGun() {
        if(rocket == null){
            rocket = gun.shootGun();
        }
    }

    public void setGunMovingDirection(AbstractMovable.Direction direction) {
        this.gun.setMovingDirection(direction);
    }

    public void stopGun() {
       // TODO
    }

    //TODO move to absMovable
    public void move(Gun ipos /* Should be AbsMovable */, int dirX, int dirY, double moveSpeed){
        double movementX = ipos.getX() + moveSpeed * dirX;
        double movementY = ipos.getY() + moveSpeed * dirY;
        if(checkBoundaries(ipos, movementX, movementY)){
            ipos.setX(movementX);
            //ipos.setY(movementY); //TODO uncomment when absMovable is implemented.
        }
    }

    public boolean checkBoundaries(IPositionable ipos, double X, double Y){

        return  (X >= 0 && X <= (GAME_WIDTH - ipos.getWidth())) &&
                (Y >= 0 && Y <= (GAME_HEIGHT - ipos.getHeight()));
    }

    // --------- Send everything to be rendered --------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> ps = new ArrayList<>();
       // TODO Add all to be rendered

        for (AbstractSpaceShip ship : formation.getShips()) {
            ps.add(ship);
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
