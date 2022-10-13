package Trace;

import Constants.Constants;
import Entities.Animate.Bomb;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import Entities.Animate.Character.Enemy.Enemy;
import Map.Map;

import static Constants.Constants.DIRECTION.*;
import static Constants.Constants.DIRECTION;

public abstract class Trace {
    int distanceBomb = 12000;
    int distanceTarget = 50000;

    protected Enemy enemy;
    protected Bomber bomber;
    protected Map gameMap;

    public static final int[] dx = {-1, 1, 0, 0, 0};
    public static final int[] dy = {0, 0, -1, 1, 0};
    public Trace(Bomber bomber,Enemy enemy,Map gameMap) {
        this.enemy = enemy;
        this.bomber = bomber;
        this.gameMap = gameMap;
    }

    public abstract Constants.DIRECTION trace();

    public int getDistance(Enemy enemy, Bomber player) {
        return (enemy.getPixelX() - player.getTileX())
                * (enemy.getPixelX() - player.getTileX())
                + (enemy.getPixelY() - player.getPixelY())
                * (enemy.getPixelY() - player.getPixelY());
    }

    public int getDistanceBomb(Enemy enemy) {
        int distanceFromBomb = Integer.MAX_VALUE;
        for (Bomb bomb : gameMap.getBombs()) {
            int distance = (bomb.getPixelX() - enemy.getPixelX())
                    * (bomb.getPixelX() - enemy.getPixelX())
                    + (bomb.getTileY() - enemy.getPixelY())
                    * (bomb.getTileY() - enemy.getPixelY());
            distanceFromBomb = Math.min(distanceFromBomb, distance);
        }
        return distanceFromBomb;
    }


    public boolean checkFaceBoom(int X, int Y, int lengthBomb) {
        return false;
    }

    public static DIRECTION directionFactory(int rightDirection) {
        return switch (rightDirection) {
            case 0 -> UP;
            case 1 -> DOWN;
            case 2 -> LEFT;
            default -> RIGHT;
        };
    }

    public static int intFactory(DIRECTION direction) {
        return switch (direction) {
            case UP -> 0;
            case DOWN -> 1;
            case LEFT -> 2;
            default -> 3;
        };
    }
}
