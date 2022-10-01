package Trace;

import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Still.Grass;
import Map.Map;

import static Constants.Contants.*;

public abstract class Trace {
    protected final Bomber player;
    protected final Enemy enemy;
    protected final Map gameMap;

    Trace(Bomber player, Enemy enemy, Map gameMap) {
        this.player = player;
        this.enemy = enemy;
        this.gameMap = gameMap;
    }

    protected boolean isMovable(int x, int y) {
        if (x < 0 || x >= HEIGHT || y < 0 || y >= WIDTH) {
            return false;
        }
        return (gameMap.getEntity(x, y) instanceof Grass);
    }

    public abstract DIRECTION getDirection();
}
