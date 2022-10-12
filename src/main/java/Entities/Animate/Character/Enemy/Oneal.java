package Entities.Animate.Character.Enemy;

import static Constants.Constants.DIRECTION;
import static Constants.Constants.DIRECTION.*;
import static Graphics.Sprite.*;
import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Trace.BfsTrace;
import Trace.BfsVsDodgeTrace;
import Trace.RandomTrace;

public class Oneal extends Enemy{
    public Oneal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT,ONEAL_LEFT);
        animation.put(RIGHT,ONEAL_RIGHT);
        animation.put(DOWN,ONEAL_LEFT);
        animation.put(UP,ONEAL_RIGHT);
        animation.put(DESTROYED,ONEAL_DEAD);
        currentAnimate = animation.get(RIGHT);
    }

    @Override
    public DIRECTION trace(Bomber bomber, Enemy enemy, Map gameMap) {
        return new BfsVsDodgeTrace(bomber,enemy,gameMap).trace();
    }
}
