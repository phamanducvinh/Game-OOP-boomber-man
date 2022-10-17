package Trace;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Graphics.Sprite;
import Map.Map;

import static Constants.Constants.DIRECTION.*;
import static Constants.Constants.DIRECTION;
public class DistanceTrace extends Trace{

    public DistanceTrace(Bomber player, Enemy enemy, Map gameMap) {
        super(player, enemy, gameMap);
    }

    @Override
    public Constants.DIRECTION trace() {
        System.out.println(getDistance(enemy,bomber));
        if (getDistance(enemy, bomber) > 5) {
            return new RandomTrace(bomber,enemy,gameMap).trace();
        }
        return new  BfsTrace(bomber,enemy,gameMap).trace();
    }
}
