package Entities.Animate.Character;

import Constants.Contants;
import Entities.Animate.Bomb;
import Entities.Entity;
import Graphics.Sprite;
import Input.KeyInput;
import javafx.geometry.Rectangle2D;

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
    public boolean isCollision2(Entity other) {
        Rectangle2D rectangle2D = new Rectangle2D(pixelY, pixelX, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        return rectangle2D.intersects(other.getBoundary());
    }

    @Override
    public boolean isMovable() {
        //isCollision = super.isMovable();
        pixelX += velocityX;
        pixelY += velocityY;
        isCollision = false;
        isCollision = isCollision2(gameMap.getEntity(tileX+velocityX,tileY+velocityY));
        pixelX -= velocityX;
        pixelY -= velocityY;
        return !isCollision;
    }


    @Override
    public void update() {
        getDirection();
        if (velocityX == 0 && velocityY == 0) {
            return;
        }
        System.out.println(isCollision);

        updateAnimation();
        move();
    }

    private void placeBomb() {
        if (numBomb == maxBomb) {
            return;
        }
        numBomb++;
        gameMap.placeBomb(new Bomb(tileX, tileY, Sprite.BOMB[0], this));
    }

    @Override
    public void getDirection() {
        direction = keyInput.handleKeyInput();
        switch (direction) {
            case PLACE_BOMB -> {
                placeBomb();
                this.setVelocity(0, 0);
            }
            case NONE -> this.setVelocity(0, 0);
            case LEFT -> this.setVelocity(0,-defaultVelocity);
            case RIGHT -> this.setVelocity(0,defaultVelocity);
            case UP -> this.setVelocity(-defaultVelocity,0);
            case DOWN -> this.setVelocity(defaultVelocity,0);
        }
        currentAnimate = animation.get(direction);
    }
}