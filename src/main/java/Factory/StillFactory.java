package Factory;

import Entities.Entity;
import Entities.Still.Brick;
import Entities.Still.Grass;
import Entities.Still.Wall;
import Graphics.Sprite;

public class StillFactory {
    public static Entity getStill(char c, int i, int j) {
        return switch (c) {
            case '#' -> (new Wall(i, j, Sprite.wall));
            case '*' -> (new Brick(i, j, Sprite.brick));
            default -> (new Grass(i, j, Sprite.grass));
        };
    }
}
