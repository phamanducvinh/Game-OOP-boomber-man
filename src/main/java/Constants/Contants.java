package Constants;

public class Contants {
    public static final String TEXTURES_PATH = "/textures/classic.png";
    public static final String GAME_TITLE = "BOMBERMAN";

    public static final String[] FONTS = {
            Contants.class.getResource("/fonts/font1.ttf").getPath(),
            Contants.class.getResource("/fonts/font2.TTF").getPath(),
            Contants.class.getResource("/fonts/font3.otf").getPath()
    };

    public static final String MENU = Contants.class.getResource("/menu/menu.fxml").getPath();

    public static final String[] MAP_PATHS_CAMPAIGN = {
            Contants.class.getResource("/levels/campaign/level1.txt").getPath(),
            Contants.class.getResource("/levels/campaign/level2.txt").getPath(),
            Contants.class.getResource("/levels/campaign/level3.txt").getPath(),
            Contants.class.getResource("/levels/campaign/level4.txt").getPath(),
            Contants.class.getResource("/levels/campaign/level5.txt").getPath(),
            Contants.class.getResource("/levels/campaign/level6.txt").getPath()
    };

    public static final String[] MAP_PATHS_TOW_PLAYER = {
            Contants.class.getResource("levels/twoplayer/level1.txt").getPath()
    };

    public static final int HEIGHT = 13;
    public static final int WIDTH = 31;
    public static final int INF = 1000000000;

    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {-1,1,0,0};

    public enum DIRECTION{
        LEFT,
        UP,
        RIGHT,
        DOWN,
        DESTROYED,  // place bomb
        NONE
    }


    public enum EXPLOSION{
        HORIZONTAL,
        RIGHT_LAST,
        VERTICAL,
        LEFT_LAST,
        TOP_LAST,
        DOWN_LAST,
        CENTER,
    }

    public enum BOMB{
        BOMB,
        DESTROYED,
    }

    public enum MODE {
        CAMPAIGN,
        TWO_PLAYER
    }

}
