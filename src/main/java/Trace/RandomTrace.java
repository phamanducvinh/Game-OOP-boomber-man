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
        List<DIRECTION> directions = new ArrayList<>();
        int x = enemy.getTile().getKey();
        int y = enemy.getTile().getValue();
        if (isMovable(x, y - 1)) {
            directions.add(LEFT);
        }
        if (isMovable(x, y + 1)) {
            directions.add(RIGHT);
        }
        if (isMovable(x - 1, y)) {
            directions.add(UP);
        }
        if (isMovable(x + 1, y)) {
            directions.add(DOWN);
        }
        if (directions.size() > 0) {
            return directions.get(new Random().nextInt(directions.size()));
        }
        return LEFT;
    }
}
