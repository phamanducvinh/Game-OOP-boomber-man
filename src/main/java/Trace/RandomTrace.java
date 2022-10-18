package Trace;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Still.Grass;
import Map.Map;

import java.util.ArrayList;
import java.util.Random;

import static Constants.Constants.DIRECTION.*;
import static Constants.Constants.*;
public class RandomTrace extends Trace {
    public RandomTrace(Bomber player, Enemy enemy, Map gameMap) {
        super(player, enemy, gameMap);
    }

    @Override
    public DIRECTION trace() {
        if(enemy.isCollision()) {
            ArrayList<DIRECTION> availableDirection = new ArrayList<>();
            if (gameMap.getTiles(enemy.getTileX() - 1, enemy.getTileY()) instanceof Grass) {
                availableDirection.add(UP);
            }
            if (gameMap.getTiles(enemy.getTileX(), enemy.getTileY() + 1) instanceof Grass) {
                availableDirection.add(RIGHT);
            }
            if (gameMap.getTiles(enemy.getTileX() + 1, enemy.getTileY()) instanceof Grass) {
                availableDirection.add(DOWN);
            }
            if (gameMap.getTiles(enemy.getTileX(), enemy.getTileY() - 1) instanceof Grass) {
                availableDirection.add(LEFT);
            }
            int rand = new Random().nextInt(availableDirection.size());
            return availableDirection.get(rand);
        } else {
            return enemy.getDirection();
        }
    }
}
