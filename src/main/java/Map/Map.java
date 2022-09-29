package Map;

import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import Entities.Entity;
import Entities.Still.Item.Item;
import Factory.CharacterFactory;
import Factory.ItemFactory;
import Factory.StillFactory;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Constants.Contants.HEIGHT;
import static Constants.Contants.WIDTH;

public class Map {
    private Entity[][] tiles;
    private List<Character> characters;
    private List<Item> items;
    private Bomber player;
    public Map getGameMap() {
        return this;
    }
    private void resetEntities() {
        tiles = new Entity[HEIGHT][WIDTH];
        characters = new ArrayList<>();
        items = new ArrayList<>();
   }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        resetEntities();
        for (int i = 0; i < HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                char c = string.charAt(j);
                StillFactory.getStill(c,i,j);
                ItemFactory.getItem(c,i,j);
                CharacterFactory.getCharacter(c,i,j);
            }
        }
    }

    public void updateMap() {
        for(int i = 0;i< HEIGHT;++i) {
            for(int j=0;j<WIDTH;++j) {
                tiles[i][j].update();
            }
        }
//        player.update();

        for(Character character : characters) {
            character.update();
        }
    }

    public void renderMap(GraphicsContext graphicsContext) {
        for(int i=0;i<HEIGHT;++i) {
            for(int j=0;j<WIDTH;++j) {
                tiles[i][j].render(graphicsContext);
            }
        }

        for(Item item : items) {
            item.render(graphicsContext);
        }

        for (Character character : characters) {
            character.render(graphicsContext);
        }
    }

    public void addItem(Item item) {
        items.add(item);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
    public void addCharacter(Character character){
        characters.add(character);
    }
    public void removeCharacter(Character character) {
        characters.remove(character);
    }
    public Bomber getPlayer() {
        return player;
    }
    public void setPlayer(Bomber bomber){
        player = bomber;
    }
    public Entity getTiles(int x, int y) {
        return tiles[x][y];
    }
    public void setTiles(int x,int y,Entity entity) {
        tiles[x][y] = entity;
    }
}
