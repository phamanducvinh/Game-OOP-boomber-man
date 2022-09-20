package Entities.Animate.Character;

import Constants.Contants.DIRECTION;
import Entities.Animate.AnimateEntity;
import Graphics.Sprite;

public abstract class Character extends AnimateEntity {

    protected int defaultVelocity = 1;
    protected int velocityX = 0;
    protected int velocityY = 0;
    protected DIRECTION direction;

    protected int speed = 1;
    public boolean isCollision = false;
    public boolean isStand = false;

    public Character(int x, int y, Sprite sprite) {
        super( x, y, sprite);
        isStand = true;
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void addVelocity(int velocityX, int velocityY) {
        this.velocityX += velocityX;
        this.velocityY += velocityY;
    }

    public void move() {
        pixelX += velocityX;
        pixelY += velocityY;
        tileX = pixelX / Sprite.SCALED_SIZE;
        tileY = pixelY / Sprite.SCALED_SIZE;
    }


    @Override
    public void update() {
        getDirection();
        updateAnimation();
        move();
    }

    @Override
    public abstract void getDirection();
}
