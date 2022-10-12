package GameController;

import Constants.Constants;
import Map.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import static Constants.Constants.*;
import static Constants.Constants.MENU_STATUS.*;

public class MenuController {
    public Map gameMap = Map.getGameMap();
    @FXML
    void campaignMode(){
        gameMap.play();
    }

    @FXML
    void aiMode() {
        System.out.println("aiMode");

    }

    @FXML
    void guide(){
        Message.showGuide();
    }

    @FXML
    void backMenu(){
        Message.showMenu();
    }
    @FXML
    void exit() {
        System.exit(0);
    }

    @FXML
    void sound(){
        SoundController.update();
    }

    @FXML
    void resume() {
        Bomberman.menu_status = PLAYING;
        Bomberman.createStage();
    }
    public static Scene getScene() throws IOException {
        Parent root = switch (Bomberman.menu_status) {
            case GUIDE ->  FXMLLoader.load(FXML_PATH[1]);
            case GAME_OVER -> FXMLLoader.load(FXML_PATH[2]);
            case PAUSE -> FXMLLoader.load(FXML_PATH[3]);
            case GAME_WIN -> FXMLLoader.load(FXML_PATH[4]);
            default -> FXMLLoader.load(FXML_PATH[0]);
        };
        return new Scene(root);
    }
}
