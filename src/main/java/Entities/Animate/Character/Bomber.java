package Entities.Animate.Character;

import Constants.Contants;
import Entities.Entity;
import Graphics.Sprite;
import Input.KeyInput;
import javafx.geometry.Rectangle2D;

public class Bomber extends Character {

    private final KeyInput keyInput;
    private int numBomb;
    private boolean bombPass;
    private static final int DEFAULT_MAX_BOMB = 3;
    private int maxBomb;
    private final int[] DIRECT_X = new int[]{0, 1, 0, 1};
    private final int[] DIRECT_Y = new int[]{1, 0, 1, 0};

    public Bomber(int x, int y, Sprite sprite, KeyInput keyInput) {
        super(x, y, sprite);
        animation.put(Contants.DIRECTION.LEFT, Sprite.PLAYER_LEFT);
        animation.put(Contants.DIRECTION.RIGHT, Sprite.PLAYER_RIGHT);
        animation.put(Contants.DIRECTION.UP, Sprite.PLAYER_UP);
        animation.put(Contants.DIRECTION.DOWN, Sprite.PLAYER_DOWN);
        currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
        this.speed = 2;
        this.keyInput = keyInput;
        this.numBomb = 0;
        this.maxBomb = 3;
        this.bombPass = false;
        gameMap.setPlayer(this);
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

    public void resetMaxBomb() {
        this.maxBomb = DEFAULT_MAX_BOMB;
    }

    @Override
    public void update() {
        if (cntMove == 0) {
            cntMove = Sprite.SCALED_SIZE - 1;
        } else cntMove--;
        for (int i = 0; i < 2; ++i) {
            updateAnimation(cntMove);
            move();
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void getDirection() {

    }

    private void placeBomb() {
        if (numBomb == maxBomb) {
            return;
        }
        numBomb ++;

    }

    public void decreaseNumBomb() {
        numBomb--;
    }

    public void getDirection(String code) {
        if (code == null) {
            System.out.println("null");
            return;
        }
        Contants.DIRECTION handleDirection = keyInput.handleKeyInput(code);
        switch (handleDirection) {
            case UP -> this.setVelocity(-defaultVelocity, 0);
            case LEFT -> this.setVelocity(0, -defaultVelocity);
            case DOWN -> this.setVelocity(defaultVelocity, 0);
            case RIGHT -> this.setVelocity(0, defaultVelocity);
            case DESTROYED -> placeBomb();
            default -> {
            }
        }
        if (handleDirection != Contants.DIRECTION.DESTROYED && handleDirection != Contants.DIRECTION.NONE) {
            currentAnimate = animation.get(handleDirection);
            direction = handleDirection;
        }
    }
}