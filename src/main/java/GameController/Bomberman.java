package GameController;


import Constants.Contants;
import Entities.*;
import Graphics.Sprite;
import Map.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Bomberman extends Application {
    private static boolean pause = true;
    private static Stage stage;
    private static long time;
    private static final int WIDTH = 31;
    private static final int HEIGHT = 13;
    private GraphicsContext gc;
    private static final double FPS = 60.0;

    private static final Map.Message message = new Map.Message();

    private static Canvas canvas = new Canvas();
    private static GraphicsContext graphicsContext = canvas.getGraphicsContext2D();


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
        Map mapGame = new Map();
        final long startNanoTime = System.nanoTime();
        Entity.setGameMap(mapGame.getGameMap());
        mapGame.createMap(Contants.MAP_PATHS_CAMPAIGN[0]);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                time = (long) ((currentNanoTime - startNanoTime) / 60000000) + 1;
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                scene.setOnKeyPressed(keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    mapGame.pressedKey(code);
                });
                scene.setOnKeyReleased(keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    mapGame.releasedKey(code);
                });

                mapGame.updateMap();
                mapGame.renderMap(gc);
            }
        };
        timer.start();
    }


    public static long getSystemTime() {
        return time;
    }

    public static boolean isPause() {
        return pause;
    }

    public static void setPause(boolean _pause) {
        pause = _pause;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void createStage() {
        pause = false;
        canvas = new Canvas(Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);

        graphicsContext = canvas.getGraphicsContext2D();

        VBox root = new VBox(Message.)
    }
}