package GameController;


import Constants.Constants;
import Graphics.Sprite;
import Input.KeyInput;
import Map.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static Constants.Constants.*;
import static Constants.Constants.MENU_STATUS.MENU;
import static Constants.Constants.STATUS.*;
import static Graphics.Sprite.SCALED_SIZE;

public class Bomberman extends Application {
    public static MENU_STATUS menu_status;
    public static long time;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static Canvas canvas = new Canvas();
    public static GraphicsContext gc = canvas.getGraphicsContext2D();

    public static Map gameMap = Map.getGameMap();
    public static Stage stage;

    private static final double FPS = 120.0, UPS = 120.0;
    final static double timePerFrame = 1000000000.0 / FPS;
    long lastFrame = System.nanoTime();
    final double timePerUpdate = 1000000000.0 / UPS;
    ;
    long lastUpdate = System.nanoTime();
    long lastCheckTime = System.currentTimeMillis();
    int framesRate = 0, updateRate = 0;
    public static STATUS status;
    public static boolean sound;

    public static void main(String[] args) {
        Application.launch(Bomberman.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Bomberman.stage = stage;
        stage.setTitle(GAME_TITLE);
        menu_status = MENU ;
        sound = true;
        SoundController.backgroundSound.play();
        Message.showMenu();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                long now = System.nanoTime();

                if (status == PLAYING) {
                    if (now - lastFrame >= timePerFrame) {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        lastFrame = System.nanoTime();
                        gameMap.renderMap(gc);
                        framesRate++;
                    }

                    if (now - lastUpdate >= timePerUpdate) {
                        lastUpdate = System.nanoTime();
                        Message.updateBoard();
                        gameMap.updateMap();
                        updateRate++;
                    }


                    if (System.currentTimeMillis() - lastCheckTime >= 1000) {
                        System.out.println("UPS: " + updateRate);
                        lastCheckTime = System.currentTimeMillis();
                        updateRate = 0;
                        framesRate = 0;
                    }

                    time = ((currentNanoTime - startNanoTime) / 60000000) + 1;
                }
            }
        }.start();
    }

    public static void createStage() {
        status = PLAYING;
        canvas = new Canvas(SCALED_SIZE * WIDTH, SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        VBox root = new VBox(Message.getBoard(),canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            if(code.equals("P")) {
                Message.showPauseMessage();
            }

            if(code.equals("M")) {
                SoundController.update();
            }
            KeyInput.keyInput.put(code, true);
        });
        scene.setOnKeyReleased(event -> {
            String code = event.getCode().toString();
            KeyInput.keyInput.put(code, false);
        });

        stage.setScene(scene);
        stage.show();
    }

}