package Trace;

import static Constants.Contants.DIRECTION.*;

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
                return LEFT;
            case 1:
                return RIGHT;
            case 2:
                return UP;
            case 3:
                return DOWN;
            default:
                return DESTROYED;
        }
    }
}
