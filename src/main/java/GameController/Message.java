package GameController;

import Map.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

import static Constants.Constants.*;
import static Graphics.Sprite.SCALED_SIZE;

public class Message {
    public static Map gameMap = Map.getGameMap();
    public static Text stage = new Text();
    public static Text bombs = new Text();
    public static Text flame = new Text();
    public static Text speed = new Text();
    public static Text life = new Text();
    public static Text sound = new Text();

    static {
        Font font = Font.loadFont(FONT_PATH[2], 20);
        stage.setFont(font);
        bombs.setFont(font);
        flame.setFont(font);
        life.setFont(font);
        sound.setFont(font);
    }

    public static Text createText(String content, int size) {
        Font font = Font.loadFont(FONT_PATH[1], size);
        Text text = new Text(content);
        text.setFont(font);
        text.setFill(Color.WHITE);
        return text;
    }

    public static void updateBoard() {
        stage.setText("STAGE: " + gameMap.getStage());
        life.setText("LIFE: " + gameMap.getLife());
        bombs.setText("BOMBS_MAX: " + gameMap.getBombs_max());
        flame.setText("LEVEL_BOMBS: " + gameMap.getLevel_bombs());
        speed.setText("LEVEL_SPEED: " + gameMap.getLevel_speed());
        if(Bomberman.sound == false) {
            sound.setText("MIX: " + "OFF");
        } else {
            sound.setText("MIX: " + "ON");
        }
    }

    public static HBox getBoard() {
        HBox hBox = new HBox(stage,life, bombs, flame, speed, sound);
        hBox.setSpacing(100.0);
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(185, 185, 185), null, null)));
        return hBox;
    }

    public static void showNextStageMessenger() {
        Bomberman.menu_status = MENU_STATUS.NEXT_STAGE;
        Text text1 = createText("Stage " + Map.getGameMap().getStage(), 30);
        StackPane root = new StackPane(text1);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(SCALED_SIZE * WIDTH, SCALED_SIZE * HEIGHT);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), null, null)));
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            if(e.getCode().toString() == "ENTER") {
                Bomberman.stage.close();
                Bomberman.createStage();
            }
        });
        Bomberman.stage.setScene(scene);
        Bomberman.stage.show();
    }

    public static void showPause() {
        try {
            SoundController.playBackGroundSound(SOUND_PATH[1]);
            Bomberman.menu_status = MENU_STATUS.PAUSE;
            Bomberman.stage.setScene(MenuController.getScene());
            Bomberman.stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showGameOver() {
        try {
            SoundController.playBackGroundSound(SOUND_PATH[7]);
            Bomberman.menu_status = MENU_STATUS.GAME_OVER;
            Bomberman.stage.setScene(MenuController.getScene());
            Bomberman.stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showWinGame() {
        try {
            SoundController.playBackGroundSound(SOUND_PATH[8]);
            Bomberman.menu_status = MENU_STATUS.GAME_WIN;
            Bomberman.stage.setScene(MenuController.getScene());
            Bomberman.stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showMenu() {
        try {
            SoundController.playBackGroundSound(SOUND_PATH[2]);
            Bomberman.menu_status = MENU_STATUS.MENU;
            Bomberman.stage.setScene(MenuController.getScene());
            Bomberman.stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showGuide() {
        try {
            SoundController.playBackGroundSound(SOUND_PATH[1]);
            Bomberman.menu_status = MENU_STATUS.GUIDE;
            Bomberman.stage.setScene(MenuController.getScene());
            Bomberman.stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
