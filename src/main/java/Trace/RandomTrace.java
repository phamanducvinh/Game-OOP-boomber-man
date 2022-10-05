package Trace;

import Constants.Contants.DIRECTION;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Map.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Constants.Contants.DIRECTION.*;

public class RandomTrace extends Trace {
    public RandomTrace(Bomber player, Enemy enemy, Map gameMap) {
        super(player, enemy, gameMap);
    }

    @Override
    public DIRECTION getDirection() {
        if(enemy.isCollision()) {
            int rand = new Random().nextInt(4);
            return switch (rand) {
                case 0 -> LEFT;
                case 1 -> RIGHT;
                case 2 -> UP;
                default -> DOWN;
            };
        } else {
            return enemy.whichDirection();
        }
    }
}
