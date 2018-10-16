package spaceinv.model.statics;


import spaceinv.model.AbstractPositionable;
import spaceinv.model.SpaceInv;
import spaceinv.view.Assets;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
    Used to check if projectiles from ships have hit the ground

 */
public class Ground extends AbstractPositionable {
    public Ground() {
        // TODO: Fix hard coded height (20)
        super(0, SpaceInv.GAME_HEIGHT - 20, SpaceInv.GAME_WIDTH, 20);
    }
}
