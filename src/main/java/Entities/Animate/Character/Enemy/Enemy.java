package Entities.Animate.Character.Enemy;
import Constants.Constants.DIRECTION;
import Entities.Animate.AnimateEntity;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import GameController.SoundController;
import Graphics.Sprite;
import Map.Map;
import Trace.BfsTrace;
import javafx.scene.effect.Effect;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

import static Constants.Constants.SOUND_PATH;

public abstract class Enemy extends Character {
    protected int life;
    protected int score;
    public Enemy(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        this.direction = DIRECTION.DOWN;
        life = 1;
        score = 100;
    }

    public abstract DIRECTION trace(Bomber bomber,Enemy enemy,Map gameMap);
    @Override
    public void findDirection() {
        direction = trace(gameMap.getPlayer(),this,gameMap);
        switch (direction) {
            case UP -> this.setVelocity(-defaultVelocity, 0);
            case DOWN -> this.setVelocity(defaultVelocity, 0);
            case LEFT -> this.setVelocity(0, -defaultVelocity);
            case RIGHT -> this.setVelocity(0, defaultVelocity);
            default -> this.setVelocity(0,0);

        }
        currentAnimate = animation.get(direction);
    }

    @Override
    public void delete() {
        if (--life != 0) {
            destroyed = false;
            return;
        }
        gameMap.getCharacters().remove(this);
        SoundController.playSound(SOUND_PATH[5]);
    }
}
