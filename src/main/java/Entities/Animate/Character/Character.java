package Entities.Animate.Character;

import Constants.Contants.DIRECTION;
import Entities.Animate.AnimateEntity;
import Entities.Entity;
import Entities.Still.Grass;
import Entities.Still.StillEntity;
import Graphics.Sprite;
import Map.Map;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.Delayed;

public abstract class Character extends AnimateEntity {

    protected int defaultVelocity = 1;
    protected int velocityX = 0;
    protected int velocityY = 0;
    protected DIRECTION direction;

    protected int speed = 1;
    public boolean isCollision = false;
    public boolean stand = false;

    public Character(int x, int y, Sprite sprite) {
        super( x, y, sprite);

        stand = true;
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

    public boolean isMovable() {
        ArrayList<Pair<Integer, Integer>> border = getBorder();
        for (Pair<Integer, Integer> point : border) {
            int x = (point.getKey() + velocityX) / Sprite.SCALED_SIZE;
            int y = (point.getValue() + velocityY) / Sprite.SCALED_SIZE;
            StillEntity entity = gameMap.getEntity(x, y);
            if (!(entity instanceof Grass)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void update() {
        if(cntMove==0) {
            getDirection();
            currentAnimate = animation.get(direction);
            cntMove = Sprite.SCALED_SIZE-1;
        } else cntMove--;

        updateAnimation();
        if(isMovable()) move();
    }

    public abstract void getDirection();
}
