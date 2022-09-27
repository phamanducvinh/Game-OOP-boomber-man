package Entities.Animate.Character.Enemy;
import Constants.Contants.DIRECTION;
import Entities.Animate.AnimateEntity;
import Entities.Animate.Character.Character;
import Graphics.Sprite;
import Map.Map;
import Trace.BfsTrace;
import Trace.RandomTrace;
import Trace.TraceStrategy;

import java.util.Random;

public abstract class Enemy extends Character {

    public TraceStrategy traceStrategy;

    public Enemy(int x, int y, Sprite sprite) {
        super(x,y,sprite);
    }

    @Override
    public void getDirection() {
        RandomTrace bfsTrace = new RandomTrace(gameMap.getPlayer(),this, gameMap);
        direction = bfsTrace.getDirection();
        switch (direction) {

            case UP:
                this.setVelocity(-defaultVelocity,0);
                currentAnimate = animation.get(DIRECTION.LEFT);
                break;
            case DOWN:
                this.setVelocity(defaultVelocity,0);
                currentAnimate = animation.get(DIRECTION.RIGHT);
                break;
            case LEFT:
                this.setVelocity(0,-defaultVelocity);
                currentAnimate = animation.get(DIRECTION.LEFT);
                break;
            case RIGHT:
                this.setVelocity(0,defaultVelocity);
                currentAnimate = animation.get(DIRECTION.RIGHT);
                break;
            default:
                break;
        }

    }
}
