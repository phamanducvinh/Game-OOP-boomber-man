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
        if (getDistance(enemy, bomber) > distanceTarget) {
            return new RandomTrace(bomber,enemy,gameMap).trace();
        }
        int currentDistanceFromBomb = getDistanceBomb(enemy);
        if (currentDistanceFromBomb < distanceBomb) {
            return new RandomTrace(bomber,enemy,gameMap).trace();
        }
        int minDistance = getDistance(enemy, bomber);
        int rightDirection = -1;
        for (int direction = 0; direction < 4; direction++) {
            enemy.setPixelX(enemy.getPixelX() + dx[direction]);
            enemy.setPixelY(enemy.getPixelY() + dy[direction]);
            enemy.setVelocity(0, 0);
            enemy.checkCollision();
            if (!enemy.isCollision()
                    && (enemy.getPixelX() % Sprite.SCALED_SIZE == 0
                    || enemy.getPixelY() % Sprite.SCALED_SIZE == 0)) {
                int distance = getDistance(enemy, bomber);
                if (distance < minDistance) {
                    minDistance = distance;
                    rightDirection = direction;
                }
            }
            enemy.setPixelX(enemy.getPixelX() - dx[direction]);
            enemy.setPixelY(enemy.getPixelY() - dy[direction]);
        }
        if (rightDirection == -1) {
            return new RandomTrace(bomber,enemy,gameMap).trace();
        }
        return directionFactory(rightDirection);
    }
}
