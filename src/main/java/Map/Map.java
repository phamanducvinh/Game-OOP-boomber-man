package Map;

import Entities.Animate.Bomb;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Animate.Flame;
import Entities.Entity;
import Entities.Still.Item.Item;
import Factory.CharacterFactory;
import Factory.ItemFactory;
import Factory.StillFactory;
import GameController.Bomberman;
import GameController.Message;
import GameController.SoundController;
import Graphics.Sprite;
import Input.PlayerInput;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import static Constants.Constants.*;

@Getter
@Setter
public class Map {
    private int stage, life, level_bombs, level_speed, bombs_max;
    private static Map gameMap;
    private Entity[][] tiles;
    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private ArrayList<Bomb> bombs;
    private ArrayList<Flame> flames;
    private Bomber player;
    private boolean artificialIntelligenceMode = false;


    public static Map getGameMap() {
        if (gameMap == null) {
            gameMap = new Map();
        }
        return gameMap;
    }

    public Entity[][] getEntity() {
        return tiles;
    }

    public Entity getTiles(int x,int y) {
        return tiles[x][y];
    }

    public void resetEntities() {
        tiles = new Entity[HEIGHT][WIDTH];
        characters = new ArrayList<>();
        items = new ArrayList<>();
        bombs = new ArrayList<>();
        flames = new ArrayList<>();
        if (player != null) {
            player.keyInput.initialization();
        }
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        scanner.nextLine();
        resetEntities();
        for (int i = 0; i < HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                char c = string.charAt(j);
                StillFactory.getStill(c, i, j);
                ItemFactory.getItem(c, i, j);
                if (c == 'p') player = new Bomber(i, j, Sprite.PLAYER_RIGHT[0], new PlayerInput());
                CharacterFactory.getCharacter(c, i, j);
            }
        }
        characters.add(player);
    }

    public void updateMap() {
        try {
            for (int i = 0; i < HEIGHT; ++i) {
                for (int j = 0; j < WIDTH; ++j) {
                    tiles[i][j].update();
                }
            }
            characters.forEach(Character::update);
            bombs.forEach(Bomb::update);
            flames.forEach(Flame::update);


            life = player.getLife();
            bombs_max = player.getMaxBombs();
            level_bombs = player.getLengthFlame();
            level_speed = player.getSpeed();
        } catch (ConcurrentModificationException e) {
            System.out.println("Delete element in Array!");
        }
    }

    public void renderMap(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(1, 1, WIDTH, HEIGHT);
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                tiles[i][j].render(graphicsContext);
            }
        }
        items.forEach(item -> item.render(graphicsContext));
        characters.forEach(character -> character.render(graphicsContext));
        bombs.forEach(bomb -> bomb.render(graphicsContext));
        flames.forEach(flame -> flame.render(graphicsContext));
    }


    public void setTiles(int x, int y, Entity entity) {
        tiles[x][y] = entity;
    }

    public void nextStage() {
        if(stage == 5) {
            Message.showWinGame();
        } else Bomberman.menu_status = MENU_STATUS.PLAYING;

        if(Bomberman.menu_status == MENU_STATUS.PLAYING) {
            String map_path = MAP_PATHS[stage++];
            try {
                createMap(map_path);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            Message.showNextStageMessenger();
        }
    }

    public void play() {
        stage = 0;
        nextStage();
    }
}
