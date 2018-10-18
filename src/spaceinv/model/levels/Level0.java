package spaceinv.model.levels;



import spaceinv.model.Gun;
import spaceinv.model.SpaceInv;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.BattleCruiser;
import spaceinv.model.ships.Frigate;
import spaceinv.model.ships.Bomber;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.model.statics.OuterSpace;

import java.util.List;

import static spaceinv.model.levels.LevelUtils.*;

/*
    Basic level to test ships and movement of

 */
public class Level0 implements ILevel {

    private final List<AbstractSpaceShip> ships =
            addAll(
                    distribute(asList(new BattleCruiser(), 13), 5),
                    distribute(asList(new Bomber(), 10), 5),
                    distribute(asList(new Frigate(), 9), 5)
            );
            // TODO replace null above with some ship

    //@Override
    //public OuterSpace getOuterSpace() {
    //    // TODO new OuterSpace(0); // Dummy for testing usage
    //    return new OuterSpace();
    //}

    @Override
    public Ground getGround() {
        // TODO new Ground(0); // Dummy for testing usage
        return new Ground();
    }

    @Override
    public Gun getGun() {
        // TODO new Gun(0, 0, 0); // Dummy for testing usage
        return new Gun((SpaceInv.GAME_WIDTH - 50) / 2, SpaceInv.GAME_HEIGHT - 50 - 20, 50, 200 );
    }

    @Override
    public ShipFormation getFormation() {
        return new ShipFormation(ships);
    }

}
