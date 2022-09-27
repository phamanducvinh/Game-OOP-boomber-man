package Trace;

import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Map.Map;

import static Constants.Contants.DIRECTION;


public abstract class TraceStrategy {
    protected Bomber player;
    protected Enemy enemy;
    protected Map gameMap;

    public TraceStrategy(Bomber player,Enemy enemy,Map gameMap) {
        this.player = player;
        this.enemy = enemy;
        this.gameMap = gameMap;
    }
    public abstract DIRECTION getDirection();
}
