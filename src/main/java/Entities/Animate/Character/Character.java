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

import static Constants.Contants.HEIGHT;
import static Constants.Contants.WIDTH;

public abstract class Character extends AnimateEntity {

    protected int defaultVelocity;
    protected int velocityX;
    protected int velocityY;
    protected DIRECTION direction;

    protected int speed;
    protected boolean collision;

    public Character(int x, int y, Sprite sprite) {
        super( x, y, sprite);
        this.velocityX = 0;
        this.velocityY = 0;
        this.defaultVelocity = 4;
        collision = false;
        gameMap.addCharacter(this);
        speed = 1;
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
    public void move() {
        System.out.println(velocityX+ " "+velocityY);
        pixelX += velocityX;
        pixelY += velocityY;
        System.out.println(pixelX+" "+pixelY);
        tileX = pixelX / Sprite.SCALED_SIZE;
        tileY = pixelY / Sprite.SCALED_SIZE;
    }

    public boolean movable() {
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
        return (!collision);
    }

    @Override
    public void update() {
        for(int i=0;i<speed;++i) {
            getDirection();
            updateAnimation(3);
            if(movable()) move();
        }
    }

    public abstract void getDirection();
}
