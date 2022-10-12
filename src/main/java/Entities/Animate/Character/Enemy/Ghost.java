package Entities.Animate.Character.Enemy;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Trace.BfsVsDodgeTrace;
import Trace.DistanceTrace;
import Trace.HalfBfsTrace;
import Trace.RandomTrace;

import static Constants.Constants.DIRECTION.*;

public class Ghost extends Enemy{
    public Ghost(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT,Sprite.GHOST_LEFT);
        animation.put(RIGHT,Sprite.GHOST_RIGHT);
        animation.put(UP,Sprite.GHOST_LEFT);
        animation.put(DOWN,Sprite.GHOST_RIGHT);
        animation.put(DESTROYED,Sprite.GHOST_DEAD);
        currentAnimate = animation.get(RIGHT);
    }

    @Override
    public Constants.DIRECTION trace(Bomber bomber, Enemy enemy, Map gameMap) {
        return new DistanceTrace(bomber,enemy,gameMap).trace();
    }
}
