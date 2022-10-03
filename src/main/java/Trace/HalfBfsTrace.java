package Trace;

import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Still.Grass;
import Map.Map;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

import static Constants.Contants.*;
import static Constants.Contants.DIRECTION.*;

public class HalfBfsTrace extends Trace {
    private static final int[][] distance = new int[HEIGHT][WIDTH];
    private final int DISTANCE = 10;

    public HalfBfsTrace(Bomber player, Enemy enemy, Map gameMap) {
        super(player, enemy, gameMap);
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                distance[i][j] = -1;
            }
        }
    }

    private Pair<Integer, Integer> Bfs(Bomber bomber, Enemy enemy) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Pair<Integer, Integer> start = bomber.getTile();
        Pair<Integer, Integer> end = enemy.getTile();
        queue.add(start);
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                distance[i][j] = -1;
            }
        }
        distance[start.getKey()][start.getValue()] = -1;

        boolean stop = false;
        while (!queue.isEmpty()) {

            Pair<Integer, Integer> tile = queue.remove();
            int x = tile.getKey();
            int y = tile.getValue();
            for (int i = 0; i < 4; ++i) {
                int u = x + dx[i];
                int v = y + dy[i];
                if (isMovable(u, v) && distance[u][v] == -1) {
                    if (u == end.getKey() && v == end.getValue()) {
                        return new Pair<>(x, y);
                    }
                    distance[u][v] = distance[x][y] + 1;
                    if (distance[u][v] < DISTANCE) {
                        queue.add(new Pair<>(u, v));
                    }
                }
            }
        }
        return null;
    }

    @Override
    public DIRECTION getDirection() {
        Pair<Integer, Integer> trace = Bfs(player, enemy);
        if (trace == null) {
            return new RandomTrace(player,enemy,gameMap).getDirection();
        }
        int _x = trace.getKey() - enemy.getTile().getKey();
        int _y = trace.getValue() - enemy.getTile().getValue();
        if (_x == -1 && _y == 0) return UP;
        if (_x == 1 && _y == 0) return DOWN;
        if (_x == 0 && _y == -1) return LEFT;
        if (_x == 0 && _y == 1) return RIGHT;
        return DOWN;
    }
}
