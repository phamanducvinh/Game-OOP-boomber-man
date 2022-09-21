package Entities.Animate.Character.Enemy;
import Constants.Contants.DIRECTION;
import Entities.Animate.AnimateEntity;
import Entities.Animate.Character.Character;
import Graphics.Sprite;
import Map.Map;
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
        direction = new RandomTrace().getRandomDirection();
        if(direction==DIRECTION.LEFT) {
            this.setVelocity(0,-defaultVelocity);
            currentAnimate = animation.get(DIRECTION.LEFT);
        }
        if(direction==DIRECTION.RIGHT) {
            this.setVelocity(0,defaultVelocity);
            currentAnimate = animation.get(DIRECTION.RIGHT);
        }
        if(direction==DIRECTION.UP) {
            this.setVelocity(-defaultVelocity,0);
            currentAnimate = animation.get(DIRECTION.UP);
        }
        if(direction==DIRECTION.DOWN) {
            this.setVelocity(defaultVelocity,0);
            currentAnimate = animation.get(DIRECTION.DOWN);
        }
        if(direction==DIRECTION.DESTROYED) {
            this.setVelocity(0,0);
        }
    }
}
