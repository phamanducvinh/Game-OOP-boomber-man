package Features;

public interface Movable {
    public void setVelocity(int velocityX, int velocityY);

    public void setSpeed(int speed);

    public int getSpeed();

    public void addVelocity(int velocityX, int velocityY);

    public void move();
}
