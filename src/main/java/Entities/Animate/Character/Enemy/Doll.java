package Entities.Animate.Character.Enemy;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Trace.RandomTrace;

import static Constants.Constants.DIRECTION.*;

public class Doll extends Enemy{
    public Doll(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT,Sprite.DOLL_LEFT);
        animation.put(RIGHT,Sprite.DOLL_RIGHT);
        animation.put(UP,Sprite.DOLL_LEFT);
        animation.put(DOWN,Sprite.DOLL_RIGHT);
        animation.put(DESTROYED,Sprite.DOLL_DEAD);
        currentAnimate = animation.get(RIGHT);
    }

    @Override
    public Constants.DIRECTION trace(Bomber bomber, Enemy enemy, Map gameMap) {
        return new RandomTrace(bomber,enemy,gameMap).getDirection();
    }
}
