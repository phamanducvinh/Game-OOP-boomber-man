package Trace;

import static Constants.Constants.*;

import Constants.Constants;
import Entities.Animate.AnimateEntity;
import Entities.Animate.Bomb;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Still.Grass;
import Map.Map;


public abstract class Trace {
    protected final Bomber player;
    protected final Enemy enemy;
    protected final Map gameMap;
    protected static final int distanceBomb = 12000;
    protected static final int distanceTarget = 50000;
    protected static final int[] DIRECTION_X = {-1, 1, 0, 0, 0};
    protected static final int[] DIRECTION_Y = {0, 0, -1, 1, 0};
    protected static final Constants.DIRECTION[] DIRECTION_LABEL = {
            DIRECTION.UP,
            DIRECTION.DOWN,
            DIRECTION.LEFT,
            DIRECTION.RIGHT,
            DIRECTION.NONE
    };

    Trace(Bomber player, Enemy enemy, Map gameMap) {
        this.player = player;
        this.enemy = enemy;
        this.gameMap = gameMap;
    }

    protected int sqr(int x) {
        return x * x;
    }
    protected int distance(AnimateEntity firstEntity, AnimateEntity secondEntity) {
        return sqr(firstEntity.getTile().getKey() - secondEntity.getTile().getKey())
                + sqr(firstEntity.getTile().getValue() - secondEntity.getTile().getValue());
    }

    protected int playerDistance() {
        return distance(enemy, player);
    }

    protected int bombDistance() {
        int minDistance = INF;
        for (Bomb bomb : gameMap.getBombs()) {
            minDistance = Math.min(minDistance, distance(enemy, bomb));
        }
        return minDistance;
    }

    protected boolean isMovable(int x, int y) {
        if (x < 0 || x >= HEIGHT || y < 0 || y >= WIDTH) {
            return false;
        }
        return (gameMap.getEntity()[x][y] instanceof Grass);
    }

    protected boolean checkFaceBoom(int X, int Y, int lengthBomb) {
        return false;
    }
    public abstract DIRECTION trace();
}
