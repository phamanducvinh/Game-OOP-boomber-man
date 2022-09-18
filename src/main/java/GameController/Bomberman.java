package GameController;


import Constants.Contants;
import Entities.*;
import Entities.Animate.Bomber;
import Graphics.Sprite;
import Map.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bomberman extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    private static Map mapGame = Map.getGameMap();
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(Bomberman.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(Contants.GAME_TITLE);

        canvas = new Canvas(Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        mapGame.createMap(Contants.MAP_PATHS[0]);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                mapGame.updateMap();
                mapGame.renderMap(gc);
            }
        };
        timer.start();
    }
}