package Factory;

import Entities.Animate.Brick;
import Entities.Entity;
import Entities.Still.Grass;
import Entities.Still.StillEntity;
import Entities.Still.Wall;
import Graphics.Sprite;

public class StillFactory {
    public static void getStill(char c, int i, int j) {
        switch (c) {
            case '#' -> new Wall(i, j, Sprite.wall);
            case '*' -> new Brick(i, j, Sprite.brick);
            default -> new Grass(i, j, Sprite.grass);
        }
        ;
    }
}
