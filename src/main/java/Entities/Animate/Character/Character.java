package Entities.Animate.Character;

import Constants.Contants.DIRECTION;
import Entities.Animate.AnimateEntity;
import Entities.Entity;
import Entities.Still.Grass;
import Graphics.Sprite;
import Map.Map;

public abstract class Character extends AnimateEntity {

    protected int defaultVelocity = 5;
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

    public void isMovable() {
        isCollision = false;

        tileX += velocityX;
        tileY += velocityY;
        Map gameMap = Map.getGameMap();

                Entity entity = gameMap.getEntity(tileX+velocityX,tileY+velocityY);
                if(entity  instanceof Grass) {
                    return;
                }
                if(isCollision(entity)) {
                    isCollision = true;
                }
        tileX -= velocityX;tileY -= velocityY;
    }

    @Override
    public void update() {
        getDirection();
        isMovable();
        if(!isStand) {
            updateAnimation();
        }

        if(!isCollision) {
            move();
        }
    }

    @Override
    public abstract void getDirection();
}
