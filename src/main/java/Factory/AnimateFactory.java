package Factory;

import Entities.Animate.Character.Bomber;
import Entities.Still.Brick;
import Entities.Animate.Character.Enemy.Balloon;
import Entities.Entity;
import Graphics.Sprite;

public class AnimateFactory {
    public static Entity getAnimate(char c, int i, int j) {
        switch (c) {
            case '*':
                return (new Brick(j,i, Sprite.brick));
            case '1':
                return (new Balloon(j,i,Sprite.BALLOON_RIGHT[0]));
            case 'p':
                return (new Bomber(j,i,Sprite.player_right));
            default:
                return null;
        }
    }
}
