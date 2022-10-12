package Trace;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Still.Grass;
import Graphics.Sprite;
import Map.Map;
import javafx.util.Pair;
import jdk.jfr.consumer.RecordedThreadGroup;

import javax.management.QueryEval;
import java.util.LinkedList;
import java.util.Queue;

import static Constants.Constants.*;
import static Constants.Constants.DIRECTION.*;

public class BfsTrace extends Trace {

    public BfsTrace(Bomber player, Enemy enemy, Map gameMap) {
        super(player, enemy, gameMap);
    }

    @Override
    public DIRECTION trace() {
        if (enemy.getPixelX() % Sprite.SCALED_SIZE == 0 || (enemy.getPixelY() & Sprite.SCALED_SIZE) == 0) {
            return enemy.getDirection();
        }
        if (playerDistance() < 2 * sqr(Sprite.SCALED_SIZE)) {
            return new DistanceTrace(player, enemy, gameMap).trace();
        }

        int lengthBomb = player.getLengthFlame() + 1;
        Queue<Integer> direc = new LinkedList<>();
        Queue<Integer> tileX = new LinkedList<>();
        Queue<Integer> tileY = new LinkedList<>();
        boolean[][] checkPass = new boolean[WIDTH][HEIGHT];
        boolean checkFaceBooms = checkFaceBoom(enemy.getTileX(), enemy.getTileY(), lengthBomb);
        int directionDodge = -1;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                checkPass[i][j] = false;
            }
        }
        checkPass[enemy.getTileX()][enemy.getTileY()] = true;
        for (int direction = 0; direction < DIRECTION_LABEL.length; direction++) {
            int x = enemy.getTileX() + DIRECTION_X[direction];
            int y = enemy.getTileY() + DIRECTION_Y[direction];
            if (gameMap.getTiles(x, y) instanceof Grass) {
                direc.add(direction);
                tileX.add(x);
                tileY.add(y);
                checkPass[x][y] = true;
            }
        }
        while (!direc.isEmpty()) {
            int x = tileX.poll(), y = tileY.poll(), directFirst = direc.poll();
            if (directionDodge == -1 && !checkFaceBoom(enemy.getTileX() + DIRECTION_X[directFirst],
                    enemy.getTileY() + DIRECTION_Y[directFirst], lengthBomb)) {
                directionDodge = directFirst;
            }
            if (checkFaceBooms && !checkFaceBoom(x, y, lengthBomb)) {
               return DIRECTION_LABEL[directFirst];
            }
            if (x == player.getTileX() && y == player.getTileY()
                    && !checkFaceBoom(enemy.getTileX() + DIRECTION_X[directFirst],
                    enemy.getTileY() + DIRECTION_Y[directFirst], lengthBomb)) {
                return  DIRECTION_LABEL[directFirst];
            }
            for (int direction = 0; direction < DIRECTION_LABEL.length; direction++) {
                int u = x + DIRECTION_X[direction];
                int v = y + DIRECTION_Y[direction];
                if (!checkPass[u][v] && gameMap.getTiles(u, v) instanceof Grass) {
                    direc.add(directFirst);
                    tileX.add(u);
                    tileY.add(v);
                    checkPass[u][v] = true;
                }
            }
        }
        DIRECTION directionRandom = new RandomTrace(player, enemy, gameMap).trace();
        int directionRandomId = 0;
        for (int id = 0; id < DIRECTION_LABEL.length; id++){
            if (directionRandom == DIRECTION_LABEL[id]){
                directionRandomId = id;
                break;
            }
        }
        if (directionDodge == -1 && !checkFaceBoom(enemy.getTileX() + DIRECTION_X[directionRandomId],
                enemy.getTileY() + DIRECTION_Y[directionRandomId], lengthBomb)) {
            return directionRandom;
        }
        return DIRECTION_LABEL[directionDodge];
    }
}
