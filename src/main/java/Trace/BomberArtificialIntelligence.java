package Trace;

import Entities.Animate.Bomb;
import Entities.Animate.Brick;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Entity;
import Entities.Still.Grass;
import Entities.Still.Item.Item;
import Entities.Still.Item.Portal;
import Entities.Still.Wall;
import Graphics.Sprite;
import Map.Map;
import Constants.Constants.DIRECTION;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static Constants.Constants.*;

public class BomberArtificialIntelligence {
    private Bomber bomber;
    private Map gameMap;
    private int[][] distance = new int[HEIGHT][WIDTH];
    private int[][] enemyDistance = new int[HEIGHT][WIDTH];
    private int[][] directions = new int[HEIGHT][WIDTH];
    private boolean[][] flameMap = new boolean[HEIGHT][WIDTH];
    public static final int[] DIRECTION_X = {-1, 1, 0, 0};
    public static final int[] DIRECTION_Y = {0, 0, -1, 1};
    private int timer = 0;

    public BomberArtificialIntelligence(Bomber bomber, Map gameMap) {
        this.bomber = bomber;
        this.gameMap = gameMap;
    }
    private boolean nearByBrick(int x, int y) {
        for (int direction = 0; direction < DIRECTION_Y.length; direction++) {
            for (int i = 1; i <= bomber.getLengthFlame(); i++) {
                Entity entity = gameMap.getTiles(x + DIRECTION_X[direction] * i, y + DIRECTION_Y[direction] * i);
                if (entity instanceof Wall) {
                    break;
                }
                if (entity instanceof Brick) {
                    return true;
                }
            }
        }
        return false;
    }

    void addFlame(boolean[][] flameMap, int x, int y) {
        for (int direction = 0; direction < DIRECTION_Y.length; direction++) {
            for (int i = 0; i <= bomber.getLengthFlame(); i++) {
                int u = x + DIRECTION_X[direction] * i;
                int v = y + DIRECTION_Y[direction] * i;
                Entity entity = gameMap.getTiles(u, v);
                if (entity instanceof Wall || entity instanceof Brick) {
                    break;
                }
                flameMap[u][v] = true;
            }
        }
    }

    private void buildFlameMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                flameMap[i][j] = false;
            }
        }
        for (Bomb bomb : gameMap.getBombs()) {
            addFlame(flameMap, bomb.getTileX(), bomber.getTileY());
         }
    }
    private boolean nearByBomb(int x, int y) {
        return flameMap[x][y];
    }

    private boolean inBomb(int x, int y) {
        for (Bomb bomb : gameMap.getBombs()) {
            if (x == bomb.getTileX() && y == bomb.getTileY()) {
                return true;
            }
        }
        return false;
    }

    private boolean inBoard(int u, int v) {
        return (u >= 0 && u < HEIGHT && v >= 0 && v < WIDTH);
    }
    private void enemiesBreadthFirstSearch() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                enemyDistance[i][j] = Integer.MAX_VALUE;
            }
        }
        Queue<Integer> xQueue = new LinkedList<>();
        Queue<Integer> yQueue = new LinkedList<>();
        for (Character character : gameMap.getCharacters()) {
            if (character instanceof Enemy) {
                enemyDistance[character.getTileX()][character.getTileY()] = 0;
                xQueue.add(character.getTileX());
                yQueue.add(character.getTileY());
            }
        }
        while (!xQueue.isEmpty()) {
            int x = xQueue.poll();
            int y = yQueue.poll();
            for (int direction = 0; direction < 4; direction++) {
                int u = x + DIRECTION_X[direction];
                int v = y + DIRECTION_Y[direction];
                if (inBoard(u, v) && enemyDistance[u][v] == -1 && gameMap.getTiles(u, v) instanceof Grass && !inBomb(u, v)) {
                    enemyDistance[u][v] = enemyDistance[x][y] + 1;
                    xQueue.add(u);
                    yQueue.add(v);
                }
            }
        }
    }
    private void bomberBreadthFirstSearch(int[][] distance, int[][] directions, int x, int y) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                distance[i][j] = Integer.MAX_VALUE;
                directions[i][j] = -1;
            }
        }
        Queue<Integer> directionQueue = new LinkedList<>();
        Queue<Integer> xQueue = new LinkedList<>();
        Queue<Integer> yQueue = new LinkedList<>();
        distance[bomber.getTileX()][bomber.getTileY()] = 0;
        for (int direction = 0; direction < 4; direction++) {
            int u = x + DIRECTION_X[direction];
            int v = y + DIRECTION_Y[direction];
            if (inBoard(u, v) && gameMap.getTiles(u, v) instanceof Grass && !inBomb(u, v)) {
                if (!nearByBomb(x, y) && nearByBomb(u, v)) {
                    continue;
                }
                distance[u][v] = 1;
                directions[u][v] = direction;
                directionQueue.add(direction);
                xQueue.add(u);
                yQueue.add(v);
            }
        }
        while (!directionQueue.isEmpty()) {
            int u = xQueue.poll(), v = yQueue.poll(), directionFirst = directionQueue.poll();
            for (int direction = 0; direction < 4; direction++) {
                int p = u + DIRECTION_X[direction];
                int q = v + DIRECTION_Y[direction];
                if (inBoard(p, q) && distance[p][q] == Integer.MAX_VALUE
                        && gameMap.getTiles(p, q) instanceof Grass && enemyDistance[p][q] > 1) {

                    if (p == 3 && q == 3) {
                        System.out.println(distance[p][q] + " " + enemyDistance[p][q] + " " + ( gameMap.getTiles(p, q) instanceof Grass));
                    }
                    if (!nearByBomb(u, v) && nearByBomb(p, q)) {
                        continue;
                    }
                    distance[p][q] = distance[u][v] + 1;

                    if (p == 3 && q == 3) {
                        System.out.println(distance[p][q] + " " + enemyDistance[p][q] + " " + ( gameMap.getTiles(p, q) instanceof Grass));
                    }
                    directions[p][q] = directionFirst;
                    directionQueue.add(directionFirst);
                    xQueue.add(p);
                    yQueue.add(q);
                }
            }
        }
    }

    private boolean checkMove(int x, int y, int direction) {
        if (inBomb(x, y) || direction == -1) {
            return false;
        }
        int u = x + DIRECTION_X[direction];
        int v = y + DIRECTION_Y[direction];
        if (!flameMap[x][y] && flameMap[u][v]) {
            return false;
        }
        int[][] distance = new int[HEIGHT][WIDTH];
        int[][] directions = new int[HEIGHT][WIDTH];
        bomberBreadthFirstSearch(distance, directions, x, y);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (distance[i][j] > 0 && distance[i][j] != Integer.MAX_VALUE) {
                    return true;
                }
            }
        }
        return false;
    }

    private int distanceToEnemy() {
        return enemyDistance[bomber.getTileX()][bomber.getTileY()];
    }

    private int distanceToItem() {
        int result = Integer.MAX_VALUE;
        for (Item item : gameMap.getItems()) {
            if (!(item instanceof Portal) && !item.isHidden() && distance[item.getTileX()][item.getTileY()] != -1) {
                result = Math.min(result, distance[item.getTileX()][item.getTileY()]);
            }
        }
        return result;
    }

    private int distanceToBrick() {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (gameMap.getTiles(i, j) instanceof Grass && nearByBrick(i, j) && distance[i][j] != -1 && distance[i][j] < result && !nearByBomb(i, j)) {
                    result = distance[i][j];
                }
            }
        }
        return result;
    }

    private boolean portalIsOpen() {
        for (Item item : gameMap.getItems()) {
            if (item instanceof Portal && !item.isHidden() && distance[item.getTileX()][item.getTileY()] != -1) {
                return true;
            }
        }
        return false;
    }

    private DIRECTION findPortal () {
        System.out.println(inBomb(3, 3));
        for (Item item : gameMap.getItems()) {
            if (item instanceof Portal && !item.isHidden() && distance[item.getTileX()][item.getTileY()] < Integer.MAX_VALUE) {
                return Trace.directionFactory(directions[item.getTileX()][item.getTileY()]);
            }
        }
        return DIRECTION.NONE;
    }
    private DIRECTION findItem() {
        int minDistance = distanceToItem();
        for (Item item : gameMap.getItems()) {
            if (!(item instanceof Portal) && !item.isHidden() && distance[item.getTileX()][item.getTileY()] == minDistance) {
                return Trace.directionFactory(directions[item.getTileX()][item.getTileY()]);
            }
        }
        return DIRECTION.NONE;
    }


    private DIRECTION findEnemy() {
        int minDistance = distanceToEnemy();
        for (Character character : gameMap.getCharacters()) {
            if (character instanceof Enemy && distance[character.getTileX()][character.getTileY()] == minDistance) {
                return Trace.directionFactory(directions[character.getTileX()][character.getTileY()]);
            }
        }
        return DIRECTION.NONE;
    }
    private DIRECTION findGrass() {
        int xMin = 0, yMin = 0;
        int distanceMin = Integer.MAX_VALUE;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (distance[i][j] < distanceMin && gameMap.getTiles(i, j) instanceof Grass && !nearByBomb(i, j) && distanceToEnemy() > 2) {
                    distanceMin = distance[i][j];
                    xMin = i;
                    yMin = j;
                }
            }
        }
        if (distanceMin == 0) {
            return DIRECTION.NONE;
        }
        return Trace.directionFactory(directions[xMin][yMin]);
    }

    private boolean checkPlaceBomb(int x, int y) {
        if (bomber.getCountBombs() == bomber.getMaxBombs() || inBomb(x, y)) {
            return false;
        }
        boolean[][] flameMap = this.flameMap.clone();
        for (int i = 0; i < HEIGHT; i++) {
            flameMap[i] = this.flameMap[i].clone();
        }
        addFlame(flameMap, x, y);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (distance[i][j] != -1 && enemyDistance[i][j] > 5 && !flameMap[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private DIRECTION findBrick() {
        int minDistance = distanceToBrick();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (gameMap.getTiles(i, j) instanceof Grass && nearByBrick(i, j) && distance[i][j] == minDistance && !nearByBomb(i, j)
                        && checkMove(bomber.getTileX(), bomber.getTileX(), directions[i][j])) {
                    return Trace.directionFactory(directions[i][j]);
                }
            }
        }
        return DIRECTION.NONE;
    }

    public DIRECTION findDirection() {
        if (bomber.getPixelX() % Sprite.SCALED_SIZE != 0 || bomber.getPixelY() % Sprite.SCALED_SIZE != 0) {
            return bomber.getDirection();
        }
        timer++;
        if (timer == 1 || timer > 200) {
            buildFlameMap();
        }
        enemiesBreadthFirstSearch();
        bomberBreadthFirstSearch(distance, directions, bomber.getTileX(), bomber.getTileY());
        if (nearByBomb(bomber.getTileX(), bomber.getTileY())) {
            System.out.println("Grass");
            return findGrass();
        }
        System.out.println(gameMap.getCharacters().size());
        if (gameMap.getCharacters().size() == 2 && portalIsOpen()) {
            System.out.println("Portal");
            return findPortal();
        }
        if (distanceToEnemy() <= 3) {
            if (checkPlaceBomb(bomber.getTileX(), bomber.getTileY())) {
                return DIRECTION.PLACE_BOMB;
            } else {
                return findGrass();
            }
        }

        if ( nearByBrick(bomber.getTileX(), bomber.getTileY())
                && checkPlaceBomb(bomber.getTileX(), bomber.getTileY())) {
            timer = 0;
            return DIRECTION.PLACE_BOMB;
        }
        if (distanceToItem() != Integer.MAX_VALUE) {
            System.out.println("Item");
            return findItem();
        }

        if (distanceToEnemy() < 5 && bomber.getCountBombs() < bomber.getMaxBombs()) {
            return findEnemy();
        }
        System.out.println(distanceToBrick());
        if (distanceToBrick() != Integer.MAX_VALUE) {
            System.out.println("Brick");
            return findBrick();
        }
        return findGrass();
    }
}
