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
    public void getDirection() {
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
        if (gameMap.getCharacters().size() == 1) {
            //Sound.backgroundSound.stop();
            //Sound.stageCleared.setCycleCount(999);
            //Sound.stageCleared.play();
        }
        //Map.score += score;
        //Sound.playSound("EnemyDie");
    }
}
