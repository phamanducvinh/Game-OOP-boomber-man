package Factory;

import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import Entities.Animate.Character.Enemy.Oneal;
import Entities.Animate.Character.Enemy.Balloon;
import Graphics.Sprite;
import Input.PlayerInput;

public class CharacterFactory {
    public static void getCharacter(char c, int i, int j) {
         switch (c) {
            case '1' -> new Balloon(i, j, Sprite.BALLOON_RIGHT[0]);
            case '2' -> new Oneal(i, j, Sprite.ONEAL_RIGHT[0]);
            case 'p' -> new Bomber(i, j, Sprite.PLAYER_RIGHT[0], new PlayerInput());
            default -> {}
        };
    }
}
