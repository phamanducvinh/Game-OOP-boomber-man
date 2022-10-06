package GameController;


import Constants.Contants;
import Entities.*;
import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Input.KeyInput;
import Map.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Bomberman extends Application {
    public static long time;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static Canvas canvas = new Canvas();
    public static GraphicsContext gc = canvas.getGraphicsContext2D();

    public static Map gameMap = Map.getGameMap();



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

        final long startNanoTime = System.nanoTime();

        gameMap.createMap(Contants.MAP_PATHS[0]);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                time = ((currentNanoTime - startNanoTime) / 60000000) + 1;
                scene.setOnKeyPressed(keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    KeyInput.keyInput.put(code,true);
                });
                scene.setOnKeyReleased(keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    KeyInput.keyInput.put(code,false);
                });

                gameMap.updateMap();
                gameMap.renderMap(gc);
            }
        };
        timer.start();
    }

}