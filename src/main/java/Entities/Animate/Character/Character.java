package Entities.Animate.Character;

import Constants.Contants.DIRECTION;
import Entities.Animate.AnimateEntity;
import Entities.Entity;
import Entities.Still.Grass;
import Entities.Still.StillEntity;
import Graphics.Sprite;
import Map.Map;

import java.util.concurrent.Delayed;

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

    public boolean isMovable() {
        Map gameMap = Map.getGameMap();
        StillEntity entity = gameMap.getEntity(tileX+velocityX,tileY+velocityY);

        if((entity  instanceof Grass)) {
            return true;
        }

        return false;
    }

    @Override
    public void update() {
        if(cntMove==0) {
            do{
                getDirection();
            }while (isMovable()==false);
            cntMove = Sprite.SCALED_SIZE-1;
        } else cntMove--;
        updateAnimation(cntMove);
        move();
    }

    @Override
    public abstract void getDirection();
}
