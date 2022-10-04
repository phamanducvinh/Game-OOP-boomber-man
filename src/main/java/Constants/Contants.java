package Constants;

public class Contants {
    public static final String TEXTURES_PATH = "/textures/classic.png";
    public static final String GAME_TITLE = "BOMBERMAN";

    public static final String[] MAP_PATHS = {
            Contants.class.getResource("/Levels/map1.txt").getPath(),
            Contants.class.getResource("/Levels/map2.txt").getPath()
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
        DESTROYED,
        NONE,
        PLACE_BOMB
    }
}
