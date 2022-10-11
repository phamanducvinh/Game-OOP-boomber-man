package GameController;

import Constants.Constants;
import Map.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import static Constants.Constants.*;
import static Constants.Constants.MENU_STATUS.GUIDE;
import static Constants.Constants.MENU_STATUS.MENU;

public class MenuController {
    public Map gameMap = Map.getGameMap();
    @FXML
    void campaignMode(){
        gameMap.nextStage();
    }

    @FXML
    void aiMode() {
        System.out.println("aiMode");

    }

    @FXML
    void guide(){
        System.out.println("guide");
        Bomberman.menu_status = GUIDE;
        try {
            Message.showMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void backMenu(){
        Bomberman.menu_status = MENU;
        try {
            Message.showMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void exit() {
        System.exit(0);
    }

    @FXML
    void sound(){
        SoundController.update();
    }

    public static Scene getScene() throws IOException {
        Parent root = switch (Bomberman.menu_status) {
            case GUIDE ->  FXMLLoader.load(FXML_PATH[1]);
            default -> FXMLLoader.load(FXML_PATH[0]);
        };
        return new Scene(root);
    }
}
