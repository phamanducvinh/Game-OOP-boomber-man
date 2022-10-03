package Map;

import GameController.Bomberman;
import Graphics.Sprite;
import Input.Sound;
import Map.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


import java.io.IOException;

import static Constants.Contants.FONTS;
import static Constants.Contants.HEIGHT;
import static Constants.Contants.WIDTH;

public class Message {

    public static Map gameMap;

    public static Text stage = new Text();
    public static Text score = new Text();
    public static Text time = new Text();
    public static Text left = new Text();
    static {
        Font font = Font.loadFont(FONTS[2], 20);
        stage.setFont(font);
        score.setFont(font);
        time.setFont(font);
        left.setFont(font);
    }

    public static Text createText(String string, int size) {
        Font font = Font.loadFont(FONTS[1], size);
        Text text = new Text(string);
        text.setFont(font);
        text.setFill(Color.WHITE);
        return text;
    }

    public static void updateBoard() {
        Message.score.setText("SCORE " + gameMap.getScore() );
        Message.stage.setText("STAGE " + gameMap.getStage());
        Message.left.setText("LEFT " + gameMap.getPlayer().getMaxBomb());
        Message.time.setText("TIME " + 100);
    }

    public static HBox getBoard() {
        HBox hBox = new HBox(Message.stage, Message.score, Message.left, Message.time);
        hBox.setSpacing(100.0);
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(185, 185, 185),null,null)));
        return hBox;
    }

    public static void showNextStageMessenger() {
        Bomberman.setPause(true);
        Text text = createText("Stage " + gameMap.getStage(), 30);
        StackPane root = new StackPane(text);
        root.setPrefSize(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0),null,null)));
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            Bomberman.getStage().close();
            Bomberman.createStage();
        });
        Bomberman.getStage().setScene(scene);
        Bomberman.getStage().show();
    }

    public static void showDefeatMessage() {
        Bomberman.setPause(true);
        Text text1 = createText("DEFEAT!", 30);
        Text text2 = createText("Your score is " + gameMap.getScore(), 20);
        VBox root = new VBox(text1, text2);
        root.setAlignment(Pos.CENTER);  root.setSpacing(10);
        root.setPrefSize(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0),null,null)));
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> System.exit(0));
        Bomberman.getStage().setScene(scene);
        Bomberman.getStage().show();
    }

    public static void showPauseMessage() {
        Bomberman.setPause(!Bomberman.isPause());
        if (Bomberman.isPause()) {
            Text text1 = createText("PAUSE", 30);
            Text text2 = createText("Press P to continue", 20);
            VBox root = new VBox(text1, text2);
            root.setAlignment(Pos.CENTER);  root.setSpacing(10);
            root.setPrefSize(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
            root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0),null,null)));
            Scene scene = new Scene(root);
            scene.setOnKeyPressed(
                    e -> {
                        String code = e.getCode().toString();
                        if (code.equals("P")) {
                            Message.showPauseMessage();
                        }
                    });
            Bomberman.getStage().setScene(scene);
            Bomberman.getStage().show();
        } else {
            Bomberman.createStage();
        }
    }

    public static void showMenu() throws IOException {
        Sound.setBackgroundSound(Sound.playSound("Title"));
        Bomberman.getStage().setScene(MenuController.getScene());
        Bomberman.getStage().show();
    }
}