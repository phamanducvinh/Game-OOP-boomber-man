package Entities.Animate.Character.Enemy;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Trace.BfsTrace;
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
    }

    @Override
    public Constants.DIRECTION trace(Bomber bomber, Enemy enemy, Map gameMap) {
        return new BfsTrace(bomber,enemy,gameMap).trace();
    }
}
