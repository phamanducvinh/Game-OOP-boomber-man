package Constants;

import java.net.URI;
import java.net.URL;

public class Constants {
    public static final String TEXTURES_PATH = "/textures/classic.png";
    public static final String GAME_TITLE = "BOMBERMAN";

    public static final String[] MAP_PATHS = {
            Constants.class.getResource("/Levels/Campaign/map1.txt").getPath(),
            Constants.class.getResource("/Levels/Campaign/map2.txt").getPath()
    };

    public static final int HEIGHT = 13;
    public static final int WIDTH = 31;
    public static final int INF = 1000000000;

    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {-1,1,0,0};

    public static enum DIRECTION{
        LEFT,
        UP,
        RIGHT,
        DOWN,
        PLACE_BOMB,
        NONE,
        DESTROYED
    }

    public enum BOMB_STATUS {
        COUNTDOWN,
        DESTROYED,
    }

    public enum EXPLOSION {
        HORIZONTAL,
        RIGHT_LAST,
        VERTICAL,
        LEFT_LAST,
        TOP_LAST,
        DOWN_LAST,
        CENTER
    }

    public enum STATUS {
        PAUSE,
        GAME_OVER,
        PLAYING,
        EXIT
    }

    public enum MENU_STATUS {
        MENU,
        GUIDE,
        PAUSE,
        GAME_OVER
    }

    public static URL[] FXML_PATH = {
            Constants.class.getResource("/scene/MainMenu.fxml"),
            Constants.class.getResource("/scene/Guide.fxml")
    };

    public static String[] FONT_PATH = {
            Constants.class.getResource("/fonts/font1.ttf").getPath(),
            Constants.class.getResource("/fonts/font2.TTF").getPath(),
            Constants.class.getResource("/fonts/font3.otf").getPath()
    };
}
