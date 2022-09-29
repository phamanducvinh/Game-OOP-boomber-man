package Entities.Animate.Character.Enemy;
import Constants.Contants.DIRECTION;
import Entities.Animate.AnimateEntity;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import Graphics.Sprite;
import Map.Map;
import Trace.BfsTrace;

import java.util.Random;

public abstract class Enemy extends Character {
    public Enemy(int x, int y, Sprite sprite) {
        super(x,y,sprite);
    }

    public abstract DIRECTION trace(Bomber bomber,Enemy enemy,Map gameMap);
    @Override
    public void getDirection() {
        direction = trace(gameMap.getPlayer(),this,gameMap);
        switch (direction) {
            case UP -> {
                this.setVelocity(-defaultVelocity, 0);
                currentAnimate = animation.get(DIRECTION.LEFT);
            }
            case DOWN -> {
                this.setVelocity(defaultVelocity, 0);
                currentAnimate = animation.get(DIRECTION.RIGHT);
            }
            case LEFT -> {
                this.setVelocity(0, -defaultVelocity);
                currentAnimate = animation.get(DIRECTION.LEFT);
            }
            case RIGHT -> {
                this.setVelocity(0, defaultVelocity);
                currentAnimate = animation.get(DIRECTION.RIGHT);
            }
            default -> {
            }
        }

    }
}
