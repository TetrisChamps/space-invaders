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

    public void update(long now) {
        // TODO the game loop

    }

    // ------------- Increase pressure on player
    private boolean finalSpeed = false;
    private boolean incSpeed = false;

    // ---------- Interaction with GUI  -------------------------

    public void fireGun() {
       // TODO
    }

    public void moveGunLeft() {
        // TODO
    }

    public void moveGunRight() {
        // TODO
    }

    public void stopGun() {
       // TODO
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
            // TODO posables.add(rocket);
        }
        return ps;
    }

    public int getPoints() {
        return points;
    }


}
