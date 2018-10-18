package spaceinv.view;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.IPositionable;

/*
     Animation for an explosion

     See renderExplosion in SpaceInvGUI

     *** Nothing to do here ***
 */
public class Explosion implements IPositionable {

    private static final Image image = Assets.INSTANCE.explosion;
    private double x;
    private double y;
    private double sx = 0;
    private double sy = 0;
    private double width;
    private double height;
    private int frameCounter = 0;
    private GraphicsContext gc;

    public Explosion(double x, double y){
        this.x = x;
        this.y = y;
        width = 80;
        height = 80;
    }

    public void update() {
        //gc.drawImage(image, sx, sy, width, height, x - 20, y - 40, width, height);

        sx = (sx + width) % 640;
        if (sx == 0) {
            sy = (sy + height) % 480;
        }
        if (frameCounter > 48) {
            EventService.add(new Event(Event.Type.EXPLOSION_EXPLODED));
            return;
        }
        frameCounter++;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }
}
