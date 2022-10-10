package GameController;


import Constants.Contants;
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

public class Bomberman extends Application {
    public static long time;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static Canvas canvas = new Canvas();
    public static GraphicsContext gc = canvas.getGraphicsContext2D();

    public static Map gameMap = Map.getGameMap();
    public static Stage stage;

    private static final double FPS=60.0,UPS=60.0;
    final static double timePerFrame=1000000000.0/FPS;
    long lastFrame = System.nanoTime();
    final double timePerUpdate=1000000000.0/UPS;;
    long lastUpdate = System.nanoTime();
    long lastCheckTime=System.currentTimeMillis();
    int framesRate=0,updateRate=0;
    public static boolean isPause;

    public static void main(String[] args) {
        Application.launch(Bomberman.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        isPause = true;
        this.stage = stage;
        stage.setTitle(Contants.GAME_TITLE);
        stage.setScene(MenuController.getScene());
        stage.show();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                long now=System.nanoTime();

                if(!isPause) {
                    if (now - lastFrame >= timePerFrame) {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        lastFrame = System.nanoTime();
                        gameMap.renderMap(gc);
                        framesRate++;
                    }

                    if (now - lastUpdate >= timePerUpdate) {
                        lastUpdate = System.nanoTime();
                        gameMap.updateMap();
                        updateRate++;
                    }


                    if(System.currentTimeMillis() - lastCheckTime >= 1000) {
                        System.out.println("UPS: "+updateRate);
                        lastCheckTime = System.currentTimeMillis();
                        updateRate = 0;
                        framesRate = 0;
                    }

                    time = ((currentNanoTime - startNanoTime) / 60000000) + 1;
                }
            }
        }.start();
    }

    public static void createPlayingStage() throws Exception {
        canvas = new Canvas(Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        gameMap.createMap(Contants.MAP_PATHS[0]);
        scene.setOnKeyPressed(keyEvent -> {
            String code = keyEvent.getCode().toString();
            KeyInput.keyInput.put(code, true);
        });
        scene.setOnKeyReleased(keyEvent -> {
            String code = keyEvent.getCode().toString();
            KeyInput.keyInput.put(code, false);
        });
        isPause = false;
    }
}