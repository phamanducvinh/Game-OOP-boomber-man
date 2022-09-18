package Factory;

import Entities.Animate.Bomber;
import Entities.Animate.Brick;
import Entities.Entity;
import Graphics.Sprite;

public class AnimateFactory {
    public static Entity getAnimate(char c, int i, int j) {
        switch (c) {
            case '*':
                return (new Brick(j,i, Sprite.brick));
            case 'p':
                return (new Bomber(j,i,Sprite.player_right_1));
            default:
                return null;
        }
    }
}
