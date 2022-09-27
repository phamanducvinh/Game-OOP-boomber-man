package Map;

import Entities.Animate.AnimateEntity;
import Entities.Animate.Character.Bomber;
import Entities.Entity;
import Entities.Still.StillEntity;
import Factory.AnimateFactory;
import Factory.StillFactory;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.Scanner;

public class Map {
    private Entity[][] stillEntities;
    private List<Entity> animateEntities;
    private int width, height;
    private Bomber player;
    private String code;

    public Map getGameMap() {
        return this;
    }

    public List<Entity> getAnimateEntities() {
        return animateEntities;
    }

    public void getKey(String code) {
        this.code = code;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void addAnimateEntities(Entity entity) {
        animateEntities.add(entity);
    }

    public StillEntity getEntity(int x, int y) {
        return (StillEntity) stillEntities[x][y];
    }

    private void resetEntities() {
        stillEntities = new Entity[height][width];
        animateEntities = new ArrayList<>();
   }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        height = scanner.nextInt();
        width = scanner.nextInt();
        scanner.nextLine();
        resetEntities();
        for (int i = 0; i < height; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < width; j++) {
                char c = string.charAt(j);
                stillEntities[i][j] = StillFactory.getStill(c, i, j);
                Entity animateEntity = AnimateFactory.getAnimate(c, i, j);
                if (animateEntity != null) {
                    System.out.println(animateEntity);
                    if (animateEntity instanceof Bomber) {
                        player = (Bomber) animateEntity;
                    } else {
                        animateEntities.add(animateEntity);
                    }
                }
            }
        }
    }

    public void updateMap() {
        animateEntities.forEach(entity -> {
            entity.update();
        });
        if (code != null) {
            player.getDirection(code);
            player.update();
            code = null;
        }
    }

    public void renderMap(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(1, 1, width, height);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                stillEntities[i][j].render(graphicsContext);
            }
        }
        animateEntities.forEach(animateEntity -> {
            animateEntity.render(graphicsContext);
        });
        player.render(graphicsContext);
    }

    public Bomber getPlayer() {
        return player;
    }
}
