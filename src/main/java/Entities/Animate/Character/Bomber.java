package Entities.Animate.Character;

import Constants.Constants;
import Entities.Animate.Bomb;
import Entities.Animate.Brick;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Entity;
import Entities.Still.Item.Item;
import Entities.Still.Item.Portal;
import GameController.Message;
import GameController.SoundController;
import Graphics.Sprite;
import Input.KeyInput;
import javafx.geometry.Rectangle2D;
import lombok.Getter;
import lombok.Setter;

import static Constants.Constants.DIRECTION.*;
import static Constants.Constants.SOUND_PATH;

@Getter
@Setter
public class Bomber extends Character {
    private int life;
    public KeyInput keyInput;
    private int lengthFlame;
    private int maxBombs;
    private int countBombs;
    private long startLife;

    public Bomber(int x, int y, Sprite sprite, KeyInput keyInput) {
        super(x, y, sprite);
        animation.put(LEFT, Sprite.PLAYER_LEFT);
        animation.put(RIGHT, Sprite.PLAYER_RIGHT);
        animation.put(UP, Sprite.PLAYER_UP);
        animation.put(DOWN, Sprite.PLAYER_DOWN);
        animation.put(DESTROYED, Sprite.PLAYER_DEAD);

        currentAnimate = animation.get(RIGHT);
        this.keyInput = keyInput;
        this.keyInput.initialization();
        this.countBombs = 0;
        this.maxBombs = 3;
        this.lengthFlame = 1;
        this.speed = 1;
        this.life = 3;
        startLife = System.currentTimeMillis();
    }


    public boolean isCollision2(Entity other) {
        Rectangle2D rectangle2D = new Rectangle2D(pixelX + 10, pixelY + 10, Sprite.SCALED_SIZE - 25, Sprite.SCALED_SIZE - 25);
        return rectangle2D.intersects(other.getBoundary());
    }

    @Override
    public void checkCollision() {
        if (direction == NONE || direction == PLACE_BOMB) {
            stand = true;
            return;
        }
        for (Character character : gameMap.getCharacters()) {
            if (this.isCollision2(character) && character instanceof Enemy) {
                destroy();
            }
        }
        for (Item item : gameMap.getItems()) {
            if (this.isCollision2(item) && !item.isHidden()) {
                if (!(item instanceof Portal)) {
                    SoundController.playEffectSound(SOUND_PATH[10]);
                }
                item.effect(this);
            }
        }

        super.checkCollision();
        if (collision) {
            for (int i = -8 - speed; i <= 8 + speed; i++) {
                switch (direction) {
                    case LEFT, RIGHT -> pixelX += i;
                    case UP, DOWN -> pixelY +=i;
                }
                super.checkCollision();
                if (!collision) {
                    break;
                } else {
                    switch (direction) {
                        case LEFT, RIGHT -> pixelX -= i;
                        case UP, DOWN -> pixelY -=i;
                    }
                }
            }
        }
        tileX = pixelX / Sprite.SCALED_SIZE;
        tileY = pixelY / Sprite.SCALED_SIZE;
    }

    public void placeBomb() {
        if (countBombs == maxBombs) {
            return;
        }
        for(Bomb bomb : gameMap.getBombs()) {
            if(bomb.getTileX() == tileX && bomb.getTileY() == tileY) {
                return;
            }
        }

        for(Character character : gameMap.getCharacters()) {
            if(character instanceof Enemy) {
                if(character.getTileX() == tileX
                        && character.getTileY() == tileY) {
                    return;
                }
            }
        }

        if(gameMap.getTiles()[tileX][tileY] instanceof Brick) {
            return;
        }


        countBombs++;
        Bomb bomb = new Bomb(tileX, tileY, Sprite.BOMB[0], this);
        gameMap.getBombs().add(bomb);
    }

    public void explosionBomb() {
        countBombs--;
    }

    @Override
    public void findDirection() {
        direction = keyInput.handleKeyInput();
        this.setVelocity(0, 0);
        switch (direction) {
            case PLACE_BOMB -> placeBomb();
            case NONE -> this.setVelocity(0, 0);
            case LEFT -> this.setVelocity(0, -defaultVelocity);
            case RIGHT -> this.setVelocity(0, defaultVelocity);
            case UP -> this.setVelocity(-defaultVelocity, 0);
            case DOWN -> this.setVelocity(defaultVelocity, 0);
        }
        if(direction != PLACE_BOMB && direction != NONE) {
            currentAnimate = animation.get(direction);
            updateAnimation();
        }
    }

    @Override
    public void destroy() {
        if(System.currentTimeMillis() - startLife < 3000) {
            return;
        }
        SoundController.playEffectSound(SOUND_PATH[6]);
        currentAnimate = animation.get(DESTROYED);
        timeDestroyed = 20;
        destroyed = true;
    }

    @Override
    public void updateDestroyAnimation() {
        if (timeDestroyed -- >= 0) {
            updateAnimation();
        }else {
            if(--life > 0) {
                this.tileX = 1;
                this.tileY = 1;
                this.pixelY = 32;
                this.pixelX = 32;
                destroyed = false;
                startLife = System.currentTimeMillis();
                currentAnimate = animation.get(RIGHT);
                updateAnimation();
            } else {
                delete();
                Message.showGameOver();
            }
        }
    }

    @Override
    public void delete() {
        gameMap.getCharacters().remove(this);
        gameMap.setPlayer(null);
    }

}