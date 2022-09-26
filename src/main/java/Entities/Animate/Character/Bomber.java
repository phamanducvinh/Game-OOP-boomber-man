package Entities.Animate.Character;

import Constants.Contants;
import Entities.Animate.AnimateEntity;
import Entities.Animate.Bomb;
import Factory.AnimateFactory;
import Graphics.Sprite;
import Input.KeyInput;

public class Bomber extends Character {

    private final KeyInput keyInput;
    private int numBomb;
    private final int maxBomb;
    private final int[] DIRECT_X = new int[]{0, 1, 0, 1};
    private final int[] DIRECT_Y = new int[]{1, 0, 1, 0};

    public Bomber(int x, int y, Sprite sprite, KeyInput keyInput) {
        super(x, y, sprite);
        animation.put(Contants.DIRECTION.LEFT, Sprite.PLAYER_LEFT);
        animation.put(Contants.DIRECTION.RIGHT, Sprite.PLAYER_RIGHT);
        animation.put(Contants.DIRECTION.UP, Sprite.PLAYER_UP);
        animation.put(Contants.DIRECTION.DOWN, Sprite.PLAYER_DOWN);
        currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
        this.keyInput = keyInput;
        this.numBomb = 0;
        this.maxBomb = 3;
    }

    @Override
    public void update() {
        if (!isMovable()) {
            return;
        }
        if (cntMove == 0) {
            cntMove = Sprite.SCALED_SIZE - 1;
        } else cntMove--;
        for (int i = 0; i < 2; ++i) {
            updateAnimation(cntMove);
            move();
        }
    }

    @Override
    public void getDirection() {

    }

    private void placeBomb() {
        if (numBomb == maxBomb) {
            return;
        }
        numBomb ++;
        gameMap.addAnimateEntities(new Bomb(tileX, tileY, Sprite.bomb, this));
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
            case DESTROYED:
                placeBomb();
                break;
            default:
                break;
        }
        if (handleDirection != Contants.DIRECTION.DESTROYED && handleDirection != Contants.DIRECTION.NONE) {
            currentAnimate = animation.get(handleDirection);
            direction = handleDirection;
        }
    }
}