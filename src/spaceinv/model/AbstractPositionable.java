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

    public boolean intersects(IPositionable positionable) {
        return !(this.x + this.width < positionable.getX() ||
                this.x > positionable.getX() + positionable.getWidth() ||
                this.y + this.height < positionable.getY() ||
                this.y > positionable.getY() + positionable.getHeight());
    }

    public boolean contains(IPositionable positionable) {
        return (positionable.getX() >= this.x &&
                positionable.getX() + positionable.getWidth() <= this.x + this.width &&
                positionable.getY()>= this.y &&
                positionable.getY() + positionable.getHeight() <= this.y + this.height);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
