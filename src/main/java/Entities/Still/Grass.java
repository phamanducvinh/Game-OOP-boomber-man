package Entities.Still;

import Graphics.Sprite;

public class Grass extends StillEntity {
    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = false;
    }
}
