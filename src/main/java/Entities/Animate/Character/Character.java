package Entities.Animate.Character;

import Constants.Contants.DIRECTION;
import Entities.Animate.AnimateEntity;
import Entities.Entity;
import Entities.Still.Grass;
import Entities.Still.StillEntity;
import Features.Movable;
import Graphics.Sprite;
import Map.Map;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.Delayed;

import static Constants.Contants.DIRECTION.LEFT;
import static Constants.Contants.DIRECTION.NONE;
import static Constants.Contants.HEIGHT;
import static Constants.Contants.WIDTH;

public abstract class Character extends AnimateEntity implements Movable {

    protected int defaultVelocity;
    protected int velocityX;
    protected int velocityY;
    protected DIRECTION direction;
    protected int speed;
    protected boolean collision;
    protected boolean stand;

    public Character(int x, int y, Sprite sprite) {
        super( x, y, sprite);
        defaultVelocity = 1;
        velocityX = 0;
        velocityY = 0;
        stand = true;
        collision = false;
        speed = 1;
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public void checkCollision() {
        collision = false;
        pixelX += this.velocityX;
        pixelY += this.velocityY;
        for(int i=0;i< HEIGHT ;++i) {
            for(int j=0;j<WIDTH;++j) {
                Entity entity = gameMap.getEntity(i,j);
                if(entity.isBlock() && this.isCollision(entity)) {
                    collision = true;
                }
            }
        }
        stand = collision || (velocityX==0&& velocityY ==0);
        pixelX -= this.velocityX;
        pixelY -= this.velocityY;
    }

    @Override
    public void update() {
        if(isDestroyed()) {
            updateDestroyAnimation();
            return;
        }
        for(int i=0;i<speed;++i) {
            getDirection();
            checkCollision();
            if(!stand) {
                updateAnimation();
            }
            if(!collision) {
                move();
            }
        }
    }
    public abstract void getDirection();

    public boolean isCollision(){
        return collision;
    }

    public DIRECTION whichDirection() {
        return direction;
    }
}
