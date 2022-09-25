package Factory;

import Entities.Animate.AnimateEntity;
import Entities.Animate.Character.Bomber;
import Entities.Still.Brick;
import Entities.Animate.Character.Enemy.Balloon;
import Entities.Entity;
import Graphics.Sprite;
import Input.PlayerOneKeyInput;

public class AnimateFactory {
    public static Entity getAnimate(char c, int i, int j) {
        switch (c) {
            case '1':
                return (new Balloon(i,j,Sprite.BALLOON_RIGHT[0]));
            case 'p':
                return  new Bomber(i,j,Sprite.PLAYER_RIGHT[0], new PlayerOneKeyInput());
            default:
                return null;
        }
    }
}
