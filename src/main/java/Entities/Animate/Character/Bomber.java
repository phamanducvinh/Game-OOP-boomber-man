package Entities.Animate.Character;

import Constants.Contants;
import Entities.Animate.Bomb;
import Entities.Still.Grass;
import Graphics.Sprite;
import Input.KeyInput;

public class Bomber extends Character {

    private static final int REDIRECTION_DISTANCE = 12;
    private final KeyInput keyInput;
    private final int maxBomb;
    private final int[] DIRECT_X = new int[]{0, 1, 0, 1};
    private final int[] DIRECT_Y = new int[]{1, 0, 1, 0};
    private int numBomb;

    public Bomber(int x, int y, Sprite sprite, KeyInput keyInput) {
        super(x, y, sprite);
        animation.put(Contants.DIRECTION.LEFT, Sprite.PLAYER_LEFT);
        animation.put(Contants.DIRECTION.RIGHT, Sprite.PLAYER_RIGHT);
        animation.put(Contants.DIRECTION.UP, Sprite.PLAYER_UP);
        animation.put(Contants.DIRECTION.DOWN, Sprite.PLAYER_DOWN);
        currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
        this.keyInput = keyInput;
        this.keyInput.initialization();
        this.numBomb = 0;
        this.maxBomb = 3;
    }


    public void pressedKey(String code) {
        keyInput.pressedKey(code);
    }

    public void releasedKey(String code) {
        keyInput.releasedKey(code);
    }

    private void determineDirectionUP() {
        if (tileY * Sprite.SCALED_SIZE + REDIRECTION_DISTANCE > pixelY
                && gameMap.getEntity(tileX - 1, tileY) instanceof Grass) {
            updateDirection(Contants.DIRECTION.LEFT);
        }
        if ((tileY + 1) * Sprite.SCALED_SIZE - REDIRECTION_DISTANCE <= pixelY
                && gameMap.getEntity(tileX - 1, tileY + 1) instanceof Grass) {
            updateDirection(Contants.DIRECTION.RIGHT);
        }
    }

    private void determineDirectionDOWN() {
        if (tileY * Sprite.SCALED_SIZE + REDIRECTION_DISTANCE > pixelY
                && gameMap.getEntity(tileX + 1, tileY) instanceof Grass) {
            updateDirection(Contants.DIRECTION.LEFT);
        }
        if ((tileY + 1) * Sprite.SCALED_SIZE - REDIRECTION_DISTANCE <= pixelY
                && gameMap.getEntity(tileX + 1, tileY + 1) instanceof Grass) {
            updateDirection(Contants.DIRECTION.RIGHT);
        }
    }

    private void determineDirectionLEFT() {
        if (tileX * Sprite.SCALED_SIZE + REDIRECTION_DISTANCE > pixelX
                && gameMap.getEntity(tileX, tileY - 1) instanceof Grass) {
            updateDirection(Contants.DIRECTION.UP);
        }
        if ((tileX + 1) * Sprite.SCALED_SIZE - REDIRECTION_DISTANCE <= pixelX
                && gameMap.getEntity(tileX + 1, tileY - 1) instanceof Grass) {
            updateDirection(Contants.DIRECTION.DOWN);
        }
    }

    private void determineDirectionRIGHT() {
        if (tileX * Sprite.SCALED_SIZE + REDIRECTION_DISTANCE > pixelX
                && gameMap.getEntity(tileX, tileY + 1) instanceof Grass) {
            updateDirection(Contants.DIRECTION.UP);
        }
        if ((tileX + 1) * Sprite.SCALED_SIZE - REDIRECTION_DISTANCE <= pixelX
                && gameMap.getEntity(tileX + 1, tileY + 1) instanceof Grass) {
            updateDirection(Contants.DIRECTION.DOWN);
        }
    }

    private void determineDirection() {
        if (direction == Contants.DIRECTION.NONE || direction == Contants.DIRECTION.DESTROYED) {
            return;
        }
        if (isMovable()) {
            return;
        }
        switch (direction) {
            case UP -> determineDirectionUP();
            case DOWN -> determineDirectionDOWN();
            case LEFT -> determineDirectionLEFT();
            case RIGHT -> determineDirectionRIGHT();
        }
    }

    @Override
    public void update() {
        getDirection();
        determineDirection();
        if (velocityX == 0 && velocityY == 0) {
            return;
        }
        if (!isMovable()) {
            return;
        }
        if (cntMove == 0) {
            cntMove = Sprite.SCALED_SIZE * 4 - 1;
        } else {
            cntMove--;
        }
        updateAnimation(cntMove / 4);
        move();
    }

    private void placeBomb() {
        if (numBomb == maxBomb) {
            return;
        }
        numBomb++;
        gameMap.addAnimateEntities(new Bomb(tileX, tileY, Sprite.bomb, this));
    }

    public void decreaseNumBomb() {
        numBomb--;
    }

    private void updateDirection(Contants.DIRECTION direction) {
        switch (direction) {
            case UP:
                this.setVelocity(-defaultVelocity, 0);
                break;
            case LEFT:
                this.setVelocity(0, -defaultVelocity);
                break;
            case DOWN:
                this.setVelocity(defaultVelocity, 0);
                break;
            case RIGHT:
                this.setVelocity(0, defaultVelocity);
                break;
        }
        currentAnimate = animation.get(direction);
        this.direction = direction;
    }

    @Override
    public void getDirection() {
        Contants.DIRECTION handleDirection = keyInput.handleKeyInput();
        switch (handleDirection) {
            case DESTROYED:
                placeBomb();
                this.setVelocity(0, 0);
                break;
            case NONE:
                this.setVelocity(0, 0);
                break;
        }
        if (handleDirection != Contants.DIRECTION.DESTROYED && handleDirection != Contants.DIRECTION.NONE) {
            updateDirection(handleDirection);
        }
    }
}