package Trace;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Still.Grass;
import Graphics.Sprite;
import Map.Map;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

import static Constants.Constants.*;
import static Constants.Constants.DIRECTION.*;

public class HalfBfsTrace extends Trace {
    private static int time = 0;

    public HalfBfsTrace(Bomber player, Enemy enemy, Map gameMap) {
        super(player, enemy, gameMap);
    }

    @Override
    public DIRECTION trace() {
        if (enemy.getPixelX() % Sprite.SCALED_SIZE != 0 || enemy.getPixelY() % Sprite.SCALED_SIZE != 0) {
            return enemy.getDirection();
        }
        time++;
        if (time < 9) {
            return new BfsVsDodgeTrace(bomber,enemy , gameMap).trace();
        } else {
            time = time % 20;
            return new RandomTrace(bomber, enemy, gameMap).trace();
        }
    }
}
