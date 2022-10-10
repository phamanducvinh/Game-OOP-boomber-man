package GameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuContainer {
    @FXML
    void campaignMode() throws Exception {
        System.out.println("campaignMode");
        Bomberman.createPlayingStage();
    }

    @FXML
    void aiMode() {
        System.out.println("aiMode");
    }

    @FXML
    void guide() {
        System.out.println("guide");
    }

    @FXML
    void exit() {
        System.out.println("exit");
    }

    public static Scene getScene() throws IOException {
        Parent root = FXMLLoader.load(MenuContainer.class.getResource("/scene/MainMenu.fxml"));
        return new Scene(root);
    }
}
