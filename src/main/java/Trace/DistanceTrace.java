package Trace;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Graphics.Sprite;
import Map.Map;
import javafx.scene.control.Spinner;

public class DistanceTrace extends Trace{

    public DistanceTrace(Bomber player, Enemy enemy, Map gameMap) {
        super(player, enemy, gameMap);
    }
    @Override
    public Constants.DIRECTION trace() {
        if (playerDistance() > distanceTarget) {
            return new RandomTrace(player, enemy, gameMap).trace();
        }
        if (bombDistance() < distanceBomb) {
            return new RandomTrace(player, enemy, gameMap).trace();
        }

        int minDistance = playerDistance();
        Constants.DIRECTION result = Constants.DIRECTION.NONE;
        for (int direction = 0; direction < 4; direction++) {
            enemy.setPixelX(enemy.getPixelY() + DIRECTION_X[direction]);
            enemy.setPixelY(enemy.getPixelY() + DIRECTION_Y[direction]);
            enemy.setVelocity(0, 0);
            if (enemy.isCollision()
                    && (enemy.getPixelX() % Sprite.SCALED_SIZE == 0 || enemy.getPixelY() % Sprite.SCALED_SIZE == 0)) {
                if (playerDistance() < minDistance) {
                    minDistance = playerDistance();
                    result = DIRECTION_LABEL[direction];
                }
            }
            enemy.setPixelX(enemy.getPixelY() - DIRECTION_X[direction]);
            enemy.setPixelY(enemy.getPixelY() - DIRECTION_Y[direction]);
        }
        if (result == Constants.DIRECTION.NONE) {
            result = new RandomTrace(player, enemy, gameMap).trace();
        }
        return result;
    }
}
