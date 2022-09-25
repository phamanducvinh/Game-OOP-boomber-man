package Constants;

public class Contants {
    public static final String TEXTURES_PATH = "/textures/classic.png";
    public static final String GAME_TITLE = "BOMBERMAN";

    public static final String[] MAP_PATHS = {
            Contants.class.getResource("/Levels/map1.txt").getPath(),
            Contants.class.getResource("/Levels/map2.txt").getPath()
    };

    public static enum DIRECTION{
        LEFT,
        UP,
        RIGHT,
        DOWN,
        DESTROYED,
        NONE
    }
}
