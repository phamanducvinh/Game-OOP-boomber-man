package Factory;

import Entities.Entity;
import Entities.Still.Grass;
import Entities.Still.Wall;
import Graphics.Sprite;

public class StillFactory {

    public static Entity getStill(char c, int i, int j) {
        switch (c) {
            case '#':
                return (new Wall(j, i, Sprite.wall));

            case 'p':
                return (new Wall(j,i,Sprite.brick));
            default:
                return (new Grass(j, i, Sprite.grass));
        }
    }
}
