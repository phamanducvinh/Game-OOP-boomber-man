package Factory;

import Entities.Animate.Brick;
import Entities.Entity;
import Entities.Still.Grass;
import Entities.Still.Wall;
import Graphics.Sprite;

public class StillFactory {

    public static Entity getStill(char c, int i, int j) {
        switch (c) {
            case '#':
                return (new Wall(j, i, Sprite.wall));
            default:
                return (new Grass(j, i, Sprite.grass));
        }
    }
}
