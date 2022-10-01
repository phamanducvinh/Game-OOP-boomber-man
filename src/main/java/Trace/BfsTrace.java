package Trace;

import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Map.Map;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

import static Constants.Contants.*;
import static Constants.Contants.DIRECTION.*;

public class BfsTrace extends Trace {
    private static final boolean[][] distance = new boolean[HEIGHT][WIDTH];

    public BfsTrace(Bomber player, Enemy enemy, Map gameMap) {
        super(player, enemy, gameMap);
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                distance[i][j] = false;
            }
        }
    }

    private Pair<Integer, Integer> Bfs(Bomber bomber, Enemy enemy) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Pair<Integer, Integer> start = bomber.getTile();
        Pair<Integer, Integer> end = enemy.getTile();
        Pair<Integer, Integer> trace = null;
        queue.add(start);
        distance[start.getKey()][start.getValue()] = true;
        boolean stop = false;
        while (!queue.isEmpty() && !stop) {
            Pair<Integer, Integer> tile = queue.remove();
            int x = tile.getKey();
            int y = tile.getValue();
            for (int i = 0; i < 4; ++i) {
                int u = x + dx[i];
                int v = y + dy[i];
                if (isMovable(u, v) && !distance[u][v]) {
                    if (u == end.getKey() && v == end.getValue()) {
                        trace = new Pair<>(x, y);
                        stop = true;
                        break;
                    }
                    queue.add(new Pair<>(u, v));
                    distance[u][v] = true;
                }
            }
        }
        return trace;
    }

    @Override
    public DIRECTION getDirection() {
        Pair<Integer, Integer> trace = Bfs(player, enemy);
        if (trace == null) {
            return new RandomTrace(player, enemy, gameMap).getDirection();
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
