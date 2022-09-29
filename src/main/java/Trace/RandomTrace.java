package Trace;

import static Constants.Contants.DIRECTION.*;

import Constants.Contants.DIRECTION;

import java.util.Random;

public class RandomTrace implements Trace {
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
