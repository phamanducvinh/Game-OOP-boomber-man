package Trace;

import static Constants.Contants.DIRECTION.*;

import Constants.Contants.DIRECTION;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;

import java.util.Random;

public class RandomTrace implements Trace {
    @Override
    public DIRECTION getDirection() {
            int random = new Random().nextInt(4);
            return switch (random) {
                case 0 -> LEFT;
                case 1 -> RIGHT;
                case 2 -> UP;
                default -> DOWN;
            };
    }
}
