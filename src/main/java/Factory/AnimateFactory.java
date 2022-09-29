package Factory;

import Entities.Animate.AnimateEntity;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Oneal;
import Entities.Still.Brick;
import Entities.Animate.Character.Enemy.Balloon;
import Entities.Entity;
import Graphics.Sprite;
import Input.PlayerOneKeyInput;

public class AnimateFactory {
    public static Entity getAnimate(char c, int i, int j) {
        return switch (c) {
            case '1' -> (new Balloon(i, j, Sprite.BALLOON_RIGHT[0]));
            case '2' -> (new Oneal(i, j, Sprite.ONEAL_RIGHT[0]));
            case 'p' -> new Bomber(i, j, Sprite.PLAYER_RIGHT[0], new PlayerOneKeyInput());
            default -> null;
        };
    }
}
