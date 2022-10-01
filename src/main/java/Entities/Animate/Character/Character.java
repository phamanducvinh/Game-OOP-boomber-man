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
        collision = false;
        stand = true;
        speed = 1;
        gameMap.addCharacter(this);
    }

    @Override
    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void move() {
        pixelX += velocityX;
        pixelY += velocityY;
        tileX = pixelX / Sprite.SCALED_SIZE;
        tileY = pixelY / Sprite.SCALED_SIZE;
    }

    public void checkCollision() {
        collision = false;
        pixelX += velocityX;
        pixelY += velocityY;

        for(int i=0;i<HEIGHT;i++) {
            for(int j=0;j<WIDTH;++j) {
                Entity entity = gameMap.getTiles(i,j);
                if(entity.isBlock() && this.isCollision(entity)) {
                    collision = true;
                }
            }
        }
        pixelX -= velocityX;
        pixelY -= velocityY;

    }

    @Override
    public void update() {
        if(destroyed) {
            updateDestroyAnimation();
            return;
        }
        for(int i=1;i<speed;++i) {
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
}
