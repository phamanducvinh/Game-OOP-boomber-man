package Trace;

import Constants.Constants.DIRECTION;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Graphics.Sprite;
import Map.Map;

public class SpeedTrace extends Trace {
    int time = 0;
    public SpeedTrace(Bomber player, Enemy enemy, Map gameMap) {
        super(player, enemy, gameMap);
    }
    @Override
    public DIRECTION trace() {
        if ((enemy.getPixelX() % Sprite.SCALED_SIZE) != 0 || (enemy.getPixelY() % Sprite.SCALED_SIZE != 0)) {
            return enemy.getDirection();
        }
        ++time;
        if (time <= 7) {
            enemy.setSpeed(2);
            return new BfsVsDodgeTrace(bomber, enemy, gameMap).trace();
        } else {
            enemy.setSpeed(1);
            time = time % 20;
            return new RandomTrace(bomber, enemy, gameMap).trace();
        }
    }
}
