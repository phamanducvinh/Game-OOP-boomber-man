package Entities.Still;
import Graphics.Sprite;

public class Wall extends StillEntity {
    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = false;

    }
}
