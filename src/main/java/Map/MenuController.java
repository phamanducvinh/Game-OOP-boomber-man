package Map;

import Constants.Contants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;

import static Constants.Contants.MODE.*;

public class MenuController {
    private Map gameMap;

    public void campaignMode() {
        gameMap.setMode(CAMPAIGN);
        gameMap.nextStage();
    }

    public void twoPlayerMode(MouseEvent mouseEvent) {
        gameMap.setMode(TWO_PLAYER);
        gameMap.nextStage();
    }

    public void exit() {
        System.exit(0);
    }

    public static  Scene getScene() throws IOException {
        Font.loadFont(Contants.FONTS[0],30);
        Font.loadFont(Contants.FONTS[1],30);
        Font.loadFont(Contants.FONTS[2],25);
        Parent parent = FXMLLoader.load(MenuController.class.getResource("/menu/menu.fxml"));
        return new Scene(parent);
    }
}
