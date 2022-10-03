package Entities.Animate.Character;

import static Constants.Contants.DIRECTION.*;

import Constants.Contants;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Entity;
import Entities.Still.Item.Bomb;
import Graphics.Sprite;

import Input.PlayerOneKeyInput;
import javafx.geometry.Rectangle2D;

import java.util.List;

import static Constants.Contants.DIRECTION.*;
import static Graphics.Sprite.*;

public class Bomber extends Character {

    private int life ;
    private int numBomb;
    private int flameLength;
    private static final int DEFAULT_MAX_BOMB = 3;
    private int maxBomb;

    private final PlayerOneKeyInput keyInput = new PlayerOneKeyInput();

    private final int[] DIRECT_X = new int[]{0, 1, 0, 1};
    private final int[] DIRECT_Y = new int[]{1, 0, 1, 0};

    public Bomber(int x, int y, Sprite sprite) {
        super(x, y, sprite);

        animation.put(LEFT,PLAYER_LEFT);
        animation.put(RIGHT,PLAYER_RIGHT);
        animation.put(UP,PLAYER_UP);
        animation.put(DOWN,PLAYER_DOWN);
        animation.put(DESTROYED,PLAYER_DEAD);

        currentAnimate = animation.get(RIGHT);
        this.speed = 2;
        this.keyInput.initialization();
        this.numBomb = 0;
        this.maxBomb = 3;
        this.life = 3;
        this.flameLength = 3;
        gameMap.setPlayer(this);
        defaultVelocity = 1;
    }

    @Override
    public void delete() {

    }

    public boolean isCollision2(Entity other) {
        Rectangle2D rectangle2D = new Rectangle2D(pixelX + 10, pixelY + 10, Sprite.SCALED_SIZE - 25, Sprite.SCALED_SIZE - 25);
        return rectangle2D.intersects(other.getBoundary());
    }

    public int getMaxBomb() {
        return maxBomb;
    }

    public void setMaxBomb(int maxBomb){
        this.maxBomb = maxBomb;
    }
    public int getFlameLength() {
        return flameLength;
    }

    public void setFlameLength(int flameLength) {
        this.flameLength = flameLength;
    }


    public void pressedKey(String code) {
        keyInput.pressedKey(code);
    }

    public void releasedKey(String code) {
        keyInput.releasedKey(code);
    }

    @Override
    public void checkCollision() {
        if (direction == DESTROYED) {
            stand = true;
            return;
        }

        List<Character> characters = gameMap.getCharacters();
        for (Character character: characters) {
            if (this.isCollision2(character) && character instanceof Enemy) {
                destroy();
            }
        }

        super.checkCollision();

        tileX = pixelX / Sprite.SCALED_SIZE;
        tileY = pixelY / Sprite.SCALED_SIZE;
    }

    @Override
    public void update() {
        getDirection();
        if (velocityX == 0 && velocityY == 0) {
            return;
        }
        updateAnimation();
        move();

    }

    public void placeBomb() {
        if (numBomb == maxBomb) {
            return;
        }
        numBomb += 1;
        this.setVelocity(0,0);
        Bomb bomb = new Bomb(this.tileX, this.tileY, sprite, this);
        gameMap.addBomb(bomb);
    }

    public void decreaseNumBomb() {
        numBomb--;
    }

    @Override
    public void getDirection() {
        Contants.DIRECTION handleDirection = keyInput.handleKeyInput();
        switch (handleDirection) {
            case UP -> this.setVelocity(-defaultVelocity, 0);
            case LEFT -> this.setVelocity(0, -defaultVelocity);
            case DOWN -> this.setVelocity(defaultVelocity, 0);
            case RIGHT -> this.setVelocity(0, defaultVelocity);
            case DESTROYED -> {
                placeBomb();
                this.setVelocity(0, 0);
            }
            case NONE -> this.setVelocity(0, 0);
            default -> {
            }
        }
        if (handleDirection != Contants.DIRECTION.DESTROYED && handleDirection != Contants.DIRECTION.NONE) {
            currentAnimate = animation.get(handleDirection);
            direction = handleDirection;
        }
    }
}