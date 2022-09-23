package Factory;

import Entities.Entity;
import Entities.Still.Brick;
import Entities.Still.Grass;
import Entities.Still.Wall;
import Graphics.Sprite;

public class StillFactory {

    public static Entity getStill(char c, int i, int j) {
        switch (c) {
            case '#':
                return (new Wall(i, j, Sprite.wall));
            case '*':
                return (new Brick(i,j, Sprite.brick));
            default:
                return (new Grass(i, j, Sprite.grass));
        }
    }
}
