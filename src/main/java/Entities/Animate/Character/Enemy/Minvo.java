package Entities.Animate.Character.Enemy;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Trace.BfsTrace;
import Trace.BfsVsDodgeTrace;
import Trace.RandomTrace;

import static Constants.Constants.DIRECTION.*;

public class Minvo extends Enemy{
    public Minvo(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT,Sprite.MINVO_LEFT);
        animation.put(RIGHT,Sprite.MINVO_RIGHT);
        animation.put(UP,Sprite.MINVO_RIGHT);
        animation.put(DOWN,Sprite.MINVO_LEFT);
        animation.put(DESTROYED,Sprite.MINVO_DEAD);
        currentAnimate = animation.get(RIGHT);
        wallPass = true;
        bombPass = true;

    }

    @Override
    public Constants.DIRECTION trace(Bomber bomber, Enemy enemy, Map gameMap) {
        return new BfsVsDodgeTrace(bomber,enemy,gameMap).trace();
    }

    @Override
    public void update() {
        super.update();
        java.util.Random rand = new java.util.Random();
        int random = rand.nextInt(101);
        if (random == 100) {
            fire();
        }
    }

    private void fire() {
        new Fire(this.tileX, this.tileY, Sprite.fire_down[0], this.direction);
    }
}
