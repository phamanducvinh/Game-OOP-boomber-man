package Trace;

import Constants.Constants;
import Entities.Animate.Bomb;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Still.Grass;
import Map.Map;
import java.util.LinkedList;
import java.util.Queue;

import static Constants.Constants.HEIGHT;
import static Constants.Constants.WIDTH;
import static Graphics.Sprite.SCALED_SIZE;
import static Constants.Constants.DIRECTION;
public class BfsTrace extends Trace {

    public BfsTrace(Bomber bomber,Enemy enemy, Map gameMap) {
        super(bomber,enemy,gameMap);
    }

    @Override
    public Constants.DIRECTION trace() {
        if ((enemy.getPixelX() % SCALED_SIZE) != 0 || (enemy.getPixelY() % SCALED_SIZE != 0)) {
            return enemy.getDirection();
        }

        /*
        if (getDistance(enemy, bomber) <= 2 * (SCALED_SIZE * SCALED_SIZE)) {
            return new DistanceTrace(bomber,enemy,gameMap).trace();
        }*/

        int lengthBomb = bomber.getLengthFlame() + 1;
        Queue<Integer> direc = new LinkedList<>();
        Queue<Integer> tileX = new LinkedList<>();
        Queue<Integer> tileY = new LinkedList<>();
        boolean[][] checkPass = new boolean[HEIGHT][WIDTH];
        boolean CheckFaceBooms = checkFaceBoom(enemy.getTileX(), enemy.getTileY(), lengthBomb);
        int directionDodge = -1;

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                checkPass[i][j] = false;
            }
        }
        checkPass[enemy.getTileX()][enemy.getTileY()] = true;

        for (int direction = 0; direction < 5; ++direction) {
            enemy.setTileX(enemy.getTileX() + dx[direction]);
            enemy.setTileY(enemy.getTileY() + dy[direction]);

            boolean isBomb = false;
            for(Bomb bomb : gameMap.getBombs()) {
                if(bomb.getTileX() == enemy.getTileX()
                && bomb.getTileY() == enemy.getTileY()) {
                    isBomb = true;
                    break;
                }
            }

            if (enemy.getTileX() >= 0 && enemy.getTileY() >= 0
                    && (gameMap.getTiles()[enemy.getTileX()][enemy.getTileY()] instanceof Grass
                    || gameMap.getTiles()[enemy.getTileX()][enemy.getTileY()] instanceof Bomber)
                    && !isBomb
            ) {
                direc.add(direction);
                tileX.add(enemy.getTileX());
                tileY.add(enemy.getTileY());
                checkPass[enemy.getTileX()][enemy.getTileY()] = true;
            }

            enemy.setTileX(enemy.getTileX() - dx[direction]);
            enemy.setTileY(enemy.getTileY() - dy[direction]);
        }

        while (!direc.isEmpty()) {
            int X = tileX.poll(), Y = tileY.poll(), direcFirst = direc.poll();

            if (directionDodge == -1 && !checkFaceBoom(enemy.getTileX() + dx[direcFirst], enemy.getTileY() + dy[direcFirst], lengthBomb))
                directionDodge = direcFirst;

            if (CheckFaceBooms && !checkFaceBoom(X, Y, lengthBomb)) {
                return directionFactory(direcFirst);
            }

            if ((X == bomber.getTileX())
                    && (Y == bomber.getTileY())
                    && !checkFaceBoom(enemy.getTileX() + dx[direcFirst], enemy.getTileY() + dy[direcFirst], lengthBomb)) {
                return directionFactory(direcFirst);
            }

            for (int direction = 0; direction < 5; ++direction) {
                X += dx[direction];
                Y += dy[direction];

                boolean isBomb = false;
                for(Bomb bomb : gameMap.getBombs()) {
                    if(bomb.getTileX() == X
                            && bomb.getTileY() == Y) {
                        isBomb = true;
                        break;
                    }
                }

                if (X >= 0 && Y >= 0
                        && !checkPass[X][Y]
                        && (gameMap.getTiles()[X][Y] instanceof Grass
                        || gameMap.getTiles()[X][Y] instanceof Bomber)
                        && !isBomb
                ) {
                    direc.add(direcFirst);
                    tileX.add(X);
                    tileY.add(Y);
                    checkPass[X][Y] = true;
                }
                X -= dx[direction];
                Y -= dy[direction];
            }
        }

        DIRECTION directionRandom = new RandomTrace(bomber,enemy,gameMap).trace();
        if (directionDodge == -1 || !checkFaceBoom(enemy.getTileX() + dx[intFactory(directionRandom)],
                enemy.getTileY() + dy[intFactory(directionRandom)], lengthBomb)) {
            return directionRandom;
        }
        else {
            return directionFactory(directionDodge);
        }
    }

}
