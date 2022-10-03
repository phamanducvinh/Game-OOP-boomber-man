package Map;

import Constants.Contants;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import Entities.Entity;
import Entities.Still.Item.Bomb;
import Entities.Still.Item.Item;
import Factory.CharacterFactory;
import Factory.ItemFactory;
import Factory.StillFactory;
import Input.PlayerOneKeyInput;
import Input.Sound;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Constants.Contants.HEIGHT;
import static Constants.Contants.WIDTH;

public class Map {
    private int stage;
    private int score;
    private Contants.MODE mode;
    private Entity[][] tiles;
    private List<Character> characters;
    private List<Item> items;

    private List<Bomb> bombs;
    private PlayerOneKeyInput playerInput = new PlayerOneKeyInput();
    private Bomber player;
    public Map getGameMap() {
        return this;
    }


    public void pressedKey(String code) {
        player.pressedKey(code);
    }

    public void releasedKey(String code) {
        player.releasedKey(code);
    }

    private void resetEntities() {
        tiles = new Entity[HEIGHT][WIDTH];
        characters = new ArrayList<>();
        items = new ArrayList<>();
   }

    public void createMap(String mapPath) throws FileNotFoundException {
        playerInput.initialization();
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

        for(Character character : characters) {
            character.update();
        }

        player.update();

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

    public List<Character> getCharacters() {
        return characters;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public void removeBomb(Bomb bomb) {
        bombs.remove(bomb);
    }

    public void setMode(Contants.MODE mode) {
        this.mode = mode;
    }

    public Contants.MODE getMode(Contants.MODE mode) {
        return mode;
    }

    public int getScore() {
        return score;
    }

    public int getStage() {
        return stage;
    }

    public void nextStage() {
        stage += 1;
        if (Sound.getStageCleared().isPlaying()) {
            Sound.getStageCleared().stop();
        }
        Sound.getBackgroundSound().stop();
        Sound.setBackgroundSound(Sound.playSound(String.format("Area%s", stage % 2)));
        String stagePath = "/levels/" + mode + String.format("/Level%d.txt", stage);
        try {
            createMap(stagePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Message.showNextStageMessenger();
    }
}
