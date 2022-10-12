package Entities.Animate.Character.Enemy;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Trace.RandomTrace;

import static Constants.Constants.DIRECTION.*;

public class Kondoria extends Enemy{
    public Kondoria(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT,Sprite.KONDORIA_LEFT);
        animation.put(RIGHT,Sprite.KONDORIA_RIGHT);
        animation.put(UP,Sprite.KONDORIA_LEFT);
        animation.put(DOWN,Sprite.KONDORIA_RIGHT);
        animation.put(DESTROYED,Sprite.KONDORIA_DEAD);
        currentAnimate = animation.get(LEFT);
    }

    @Override
    public Constants.DIRECTION trace(Bomber bomber, Enemy enemy, Map gameMap) {
        return new RandomTrace(bomber,enemy,gameMap).getDirection();
    }
}
