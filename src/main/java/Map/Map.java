package Map;

import Entities.Entity;
import Factory.AnimateFactory;
import Factory.StillFactory;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Map {
    private static Map gameMap;
    public int WIDTH, HEIGHT;
    public static Entity[][] stillEntities;
    private static Entity[][] animateEntities;
    public static Map getGameMap() {
        if (gameMap == null) {
            gameMap = new Map();
        }
        return gameMap;
    }

    private void resetEntities() {
        stillEntities = new Entity[HEIGHT][WIDTH];
        animateEntities = new Entity[HEIGHT][WIDTH];
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
                animateEntities[i][j] = AnimateFactory.getAnimate(c,i,j);
            }
        }
    }

    public void updateMap() {
        for(int i=0;i<HEIGHT;++i) {
            for(int j=0;j<WIDTH;++j) {
                if(animateEntities[i][j] != null)
                animateEntities[i][j].update();
            }
        }
    }

    public void renderMap(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(1,1,WIDTH,HEIGHT);
        for(int i=0;i<HEIGHT;++i) {
            for(int j=0;j<WIDTH;++j) {
                if(stillEntities[i][j]!=null)
                stillEntities[i][j].render(graphicsContext);
            }
        }

        for(int i=0;i<HEIGHT;++i) {
            for (int j=0;j<WIDTH;++j) {
                if(animateEntities[i][j]!=null)
                    animateEntities[i][j].render(graphicsContext);
            }
        }
    }

}
