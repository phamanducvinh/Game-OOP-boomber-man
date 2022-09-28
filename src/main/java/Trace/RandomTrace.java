package Trace;

import static Constants.Contants.DIRECTION.*;

import Constants.Contants.DIRECTION;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Map.Map;
import Trace.TraceStrategy;

import java.util.Random;

public class RandomTrace extends TraceStrategy {
    public RandomTrace(Bomber bomber, Enemy enemy,Map map) {
        super(bomber,enemy,map);
    }
    @Override
    public DIRECTION getDirection() {
        int random = new Random().nextInt(4);
        switch (random){
            case 0:
                return LEFT;
            case 1:
                return RIGHT;
            case 2:
                return UP;
            default :
                return DOWN;
        }
    }
}
