package Entities.Animate.Character;

import Constants.Contants;
import Entities.Animate.Bomb;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Entity;
import Entities.Still.Item.Item;
import Entities.Still.Item.Portal;
import Graphics.Sprite;
import Input.KeyInput;
import javafx.geometry.Rectangle2D;

public class Bomber extends Character {
    private final int[] DIRECT_X = new int[]{0, 1, 0, 1};
    private final int[] DIRECT_Y = new int[]{1, 0, 1, 0};
    public int life ;
    public KeyInput keyInput;
    public int lengthFlame;
    private int maxBombs;
    private int countBombs;

    public Bomber(int x, int y, Sprite sprite, KeyInput keyInput) {
        super(x, y, sprite);
        animation.put(Contants.DIRECTION.LEFT, Sprite.PLAYER_LEFT);
        animation.put(Contants.DIRECTION.RIGHT, Sprite.PLAYER_RIGHT);
        animation.put(Contants.DIRECTION.UP, Sprite.PLAYER_UP);
        animation.put(Contants.DIRECTION.DOWN, Sprite.PLAYER_DOWN);
        currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
        this.keyInput = keyInput;
        this.keyInput.initialization();
        this.countBombs = 0;
        this.maxBombs = 3;
        this.lengthFlame = 2;
        this.life = 3;
    }
    public boolean isCollision2(Entity other) {
        Rectangle2D rectangle2D = new Rectangle2D(pixelY+10, pixelX+10, Sprite.SCALED_SIZE-25, Sprite.SCALED_SIZE-25);
        return rectangle2D.intersects(other.getBoundary());
    }


    @Override
    public void checkCollision() {
        if(direction == null) {
            stand = true;
            return;
        }
        for (Character character : gameMap.getCharacters()) {
            if(this.isCollision2(character) && character instanceof Enemy) {
                destroy();
            }
        }

        for(Item item : gameMap.getItems()) {
            if(this.isCollision2(item) && !item.isHidden()) {
                if(!(item instanceof Portal)) {
                    // Sound Level up
                }
                item.effect(this);
            }
        }

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

    private void placeBomb() {
        if (countBombs == maxBombs) {
            return;
        }
        countBombs++;
        gameMap.placeBomb(new Bomb(tileX, tileY, Sprite.BOMB[0], this));
        // gameMap.addBomb
    }

    @Override
    public void getDirection() {
        direction = keyInput.handleKeyInput();
        this.setVelocity(0,0);
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

    @Override
    public void delete() {
        //Sound.playSound("Die");
        life -= 1;
        this.destroyed = false;
        this.tileX = 1;     this.tileY = 1;
        this.pixelX = 32;   this.pixelY = 32;
        currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
        updateAnimation();
        if (life == 0) {
            //Message.showDefeatMessage();
            //Sound.backgroundSound.stop();
            //Sound.playSound("GameOver");
        }
    }

    public int getCountBombs() {
        return countBombs;
    }

    public void setBomb() {
        if(countBombs<maxBombs) {
            countBombs++;
        }
    }

    public void removeBomb() {
        countBombs--;
    };

    public int getLengthFlame() {
        return lengthFlame;
    }


}