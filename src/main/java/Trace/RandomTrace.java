package Trace;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Map.Map;
import java.util.Random;

import static Constants.Constants.DIRECTION.*;
import static Constants.Constants.*;
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
            return enemy.direction;
        }
    }
}
