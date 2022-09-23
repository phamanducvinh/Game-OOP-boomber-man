package Trace;

import Constants.Contants.DIRECTION;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Trace.TraceStrategy;

import java.util.Random;

public class RandomTrace implements TraceStrategy {
    public DIRECTION getRandomDirection() {
        int random = new Random().nextInt(4);
        switch (random){
            case 0:
                return DIRECTION.LEFT;
            case 1:
                return DIRECTION.RIGHT;
            case 2:
                return DIRECTION.UP;
            case 3:
                return DIRECTION.DOWN;
            default:
                return DIRECTION.DESTROYED;

        }
    }
}
