package spaceinv.model;

/*
   Contract for anything that can be positioned in the world.
   Used by GUI. This must be fulfilled by any object the GUI shall render
 */
public interface IPositionable {

    double getX();      // MinX and Y is upper left corner (y-axis pointing donw)

    double getY();

    double getWidth();

    double getHeight();

}
