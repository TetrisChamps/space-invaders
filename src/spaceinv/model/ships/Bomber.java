package spaceinv.model.ships;

import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.projectiles.Bomb;

import java.util.Random;

/*
 *   Type of space ship
 */
public class Bomber extends AbstractSpaceShip {

    static Random rand = new Random();
    private final float dropChance;
    ;

    public Bomber() {
        super(0, 0, 20, 20);
        dropChance = 0.003f;
    }

    @Override
    public Object copyOf() {
        return new Bomber();
    }

    public void dropBomb(){
        double dropValue = rand.nextFloat();
        if(dropValue <= dropChance){
            EventService.add(new Event(Event.Type.BOMB_DROPPED, new Bomb(
                    this.getX() + getWidth()/2 - Bomb.BOMB_WIDTH/2,
                    this.getY() + getHeight() - Bomb.BOMB_HEIGHT / 2, 200))
            );
        }
    }
}
