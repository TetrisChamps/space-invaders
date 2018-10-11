package spaceinv.model;

public abstract class AbstractPositionable implements IPositionable {
    private double x;
    private double y;
    private double width;
    private double height;

    public AbstractPositionable(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    @Override
    public boolean intersects(IPositionable positionable) {
        return !(this.x + this.width < positionable.getX() ||
                this.x > positionable.getX() + positionable.getWidth() ||
                this.y + this.height < positionable.getY() ||
                this.y > positionable.getY() + positionable.getHeight());
    }

    @Override
    public boolean contains(IPositionable positionable) {
        return (this.x >= positionable.getX() &&
                this.x + this.width <= positionable.getX() + positionable.getWidth() &&
                this.y >= positionable.getY() &&
                this.y + this.height <= positionable.getY() + positionable.getHeight());
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
