package spaceinv.model;

public class AbstractPositionable implements IPositionable {
    private int x;
    private int y;
    private int width;
    private int height;


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
