package Constants;

public class Contants {
    public static final String TEXTURES_PATH = "/textures/classic.png";
    public static final String GAME_TITLE = "BOMBERMAN";

    public static final String[] MAP_PATHS = {
            Contants.class.getResource("/levels/map1.txt").getPath(),
            Contants.class.getResource("/levels/map2.txt").getPath()
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

}
