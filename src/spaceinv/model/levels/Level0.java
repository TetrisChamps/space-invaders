package spaceinv.model.levels;



import spaceinv.model.Gun;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.BattleCruiser;
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
                    distribute(asList(new BattleCruiser(), 4), 5)
            );
            // TODO replace null above with some ship

    @Override
    public OuterSpace getOuterSpace() {
        // TODO new OuterSpace(0); // Dummy for testing usage
        return new OuterSpace();
    }

    @Override
    public Ground getGround() {
        // TODO new Ground(0); // Dummy for testing usage
        return new Ground();
    }

    @Override
    public Gun getGun() {
        // TODO new Gun(0, 0, 0); // Dummy for testing usage
        return new Gun();
    }

    @Override
    public ShipFormation getFormation() {
        return new ShipFormation(ships);
    }

}
