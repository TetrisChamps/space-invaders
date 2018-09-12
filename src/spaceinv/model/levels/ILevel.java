package spaceinv.model.levels;

import spaceinv.model.Gun;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.model.statics.OuterSpace;

import java.util.List;

/*
   A Level will supply all object needed in the OO model for the game

   NOTE: Original Space Invaders seems not to have any levels.
   Anyway nice for testing.

   *** Nothing to do here ***

 */
public interface ILevel {

    OuterSpace getOuterSpace();    // To stop rocket

    Ground getGround();            // To stop bombs

    Gun getGun();

    ShipFormation getFormation();
}
