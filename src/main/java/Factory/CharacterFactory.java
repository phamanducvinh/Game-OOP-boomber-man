package Factory;

import Entities.Animate.Character.Character;
import Entities.Animate.Character.Enemy.Oneal;
import Entities.Animate.Character.Enemy.Balloon;
import Graphics.Sprite;

public class CharacterFactory {
    public static Character getCharacter(char c, int i, int j) {
        return switch (c) {
            case '1' -> (new Balloon(i, j, Sprite.BALLOON_RIGHT[0]));
            case '2' -> (new Oneal(i, j, Sprite.ONEAL_RIGHT[0]));
            //case 'p' -> new Bomber(i, j, Sprite.PLAYER_RIGHT[0], new PlayerOneKeyInput());
            default -> null;
        };
    }
}
