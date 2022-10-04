package Map;

import Entities.Animate.Bomb;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import Entities.Entity;
import Entities.Still.Item.Item;
import Entities.Still.StillEntity;
import Factory.CharacterFactory;
import Factory.ItemFactory;
import Factory.PlayerFactory;
import Factory.StillFactory;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static Constants.Contants.*;

public class Map {
    private static Map gameMap;
    private static StillEntity[][] tiles;
    private static ArrayList<Item> items;
    private static ArrayList<Character> characters;
    private static ArrayList<Bomb> bombs;
    private Bomber player;

    public Map getGameMap() {
        if(gameMap == null) {
            gameMap = new Map();
        }
        return gameMap;
    }


    public StillEntity getEntity(int x, int y) {
        return (StillEntity) tiles[x][y];
    }

    private void resetEntities() {
        tiles = new StillEntity[HEIGHT][WIDTH];
        characters = new ArrayList<>();
        items = new ArrayList<>();
        bombs = new ArrayList<>();
   }

   private void getCharacter(char c,int x,int y) {
        Character character = CharacterFactory.getCharacter(c,x,y);
        if(character != null) {
            characters.add(character);
        }
    }

    private void getItem(char c,int x,int y) {
        Item item = ItemFactory.getItem(c,x,y);
        if(item != null) {
            items.add(item);
        }
    }

    private void getBomber(char c,int x,int y) {
        Bomber bomber = PlayerFactory.getPlayer(c,x,y);
        if(bomber != null) {
            player = bomber;
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
                tiles[i][j] = StillFactory.getStill(c, i, j);
                getCharacter(c,i,j);
                getItem(c,i,j);
                getBomber(c,i,j);
                getBomber(c,i,j);
            }
        }
    }

    public void updateMap() {
        for(int i=0;i<HEIGHT;++i) {
            for(int j=0;j<WIDTH;++j) {
                tiles[i][j].update();
            }
        }
        characters.forEach(Character::update);
        bombs.forEach(Bomb :: update);
        player.update();
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
        player.render(graphicsContext);
    }


    public StillEntity[][] getTiles() {
        return tiles;
    }

    public void setTiles(int x,int y,StillEntity stillEntity) {
        tiles[x][y] = stillEntity;
    }

    public Bomber getPlayer() {
        return player;
    }
    public void removeItem(Item item) {
        items.remove(item);
    }

    public void placeBomb(Bomb bomb) {
        bombs.add(bomb);
    }

}
