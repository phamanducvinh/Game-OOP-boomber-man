package Map;
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
import java.util.Scanner;

public class Map {
    private static Map gameMap;
    public int WIDTH, HEIGHT;
    public static Entity[][] stillEntities;

    public Bomber player;
    List<Entity> animateEntities;
    public static Map getGameMap() {
        if (gameMap == null) {
            gameMap = new Map();

        }
        return gameMap;
    }

    public int getHeightMap() {
        return HEIGHT;
    }

    public int getWidthMap() {
        return WIDTH;
    }

    public StillEntity getEntity(int x,int y) {
        return (StillEntity) stillEntities[x][y];
    }

    private void resetEntities() {
        stillEntities = new Entity[HEIGHT][WIDTH];
        animateEntities = new ArrayList<Entity>();
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();
        scanner.nextLine();
        resetEntities();
        for (int i = 0; i < HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                char c = string.charAt(j);
                stillEntities[i][j] = StillFactory.getStill(c, i, j);
                Entity animateEntity =  AnimateFactory.getAnimate(c,i,j);
                if(animateEntity != null) {
                    System.out.println(animateEntity);
                    animateEntities.add(animateEntity);
                }
            }
        }
    }

    public void updateMap() {
        animateEntities.forEach(entity -> {
            entity.update();
        });
    }

    public void renderMap(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(1,1,WIDTH,HEIGHT);
        for(int i=0;i<HEIGHT;++i) {
            for(int j=0;j<WIDTH;++j) {
                stillEntities[i][j].render(graphicsContext);
            }
        }

        animateEntities.forEach(animateEntity -> {
            animateEntity.render(graphicsContext);
        });
    }

}
