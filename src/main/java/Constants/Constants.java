package Constants;

import java.net.URI;
import java.net.URL;

public class Constants {
    public static final String TEXTURES_PATH = "/textures/classic.png";
    public static final String GAME_TITLE = "BOMBERMAN";

    public static final String[] MAP_PATHS = {
            Constants.class.getResource("/Levels/Campaign/map1.txt").getPath(),
            Constants.class.getResource("/Levels/Campaign/map2.txt").getPath(),
            Constants.class.getResource("/Levels/Campaign/map3.txt").getPath(),
            Constants.class.getResource("/Levels/Campaign/map4.txt").getPath(),
            Constants.class.getResource("/Levels/Campaign/map5.txt").getPath()
    };

    public static final int HEIGHT = 13;
    public static final int WIDTH = 31;
    public static final int INF = 1000000000;

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static enum DIRECTION {
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

    public enum MENU_STATUS {
        MENU,
        GUIDE,
        PAUSE,
        GAME_OVER,
        GAME_WIN,

        PLAYING,
        NEXT_STAGE
    }

    public static URL[] FXML_PATH = {
            Constants.class.getResource("/scene/MainMenu.fxml"),
            Constants.class.getResource("/scene/Guide.fxml"),
            Constants.class.getResource("/scene/GameOver.fxml"),
            Constants.class.getResource("/scene/Pause.fxml"),
            Constants.class.getResource("/scene/WinGame.fxml")
    };

    public static String[] FONT_PATH = {
            Constants.class.getResource("/fonts/font1.ttf").getPath(),
            Constants.class.getResource("/fonts/font2.TTF").getPath(),
            Constants.class.getResource("/fonts/font3.otf").getPath()
    };

    public static String[] SOUND_PATH = {
            Constants.class.getResource("/sound/Area0.wav").getPath(),       // 0
            Constants.class.getResource("/sound/StageCleared.wav").getPath(),// 1 Clear
            Constants.class.getResource("/sound/Title.wav").getPath(),       // 2
            Constants.class.getResource("/sound/Explosion.wav").getPath(),   // 3 Bomb explosion
            Constants.class.getResource("/sound/ItemAppears.wav").getPath(), // 4 Item
            Constants.class.getResource("/sound/EnemyDie.wav").getPath(),    // 5 Enemy die
            Constants.class.getResource("/sound/Die.wav").getPath(),         // 6 Bomber die
            Constants.class.getResource("/sound/GameOver.wav").getPath(),    // 7 Game over
            Constants.class.getResource("/sound/Victory.wav").getPath(),      // 8 Win game
            Constants.class.getResource("/sound/Pause.wav").getPath(),    //9 Pause
            Constants.class.getResource("/sound/PowerUp.wav").getPath()    //10 Power up
    };
}
