package Factory;

import Entities.Entity;
import Entities.Animate.Brick;
import Entities.Still.Grass;
import Entities.Still.Wall;
import Graphics.Sprite;

public class StillFactory {
    public static Entity getStill(char c, int i, int j) {
        return switch (c) {
            case '#' -> (new Wall(i, j, Sprite.WALL));
            case '*' -> (new Brick(i, j, Sprite.BRICK));
            default -> (new Grass(i, j, Sprite.GRASS));
        };
    }
}
