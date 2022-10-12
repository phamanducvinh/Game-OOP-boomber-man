package Factory;

import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Character;
import Entities.Animate.Character.Enemy.*;
import Graphics.Sprite;
import Input.PlayerInput;

public class CharacterFactory {
    public static void getCharacter(char c, int i, int j) {
         switch (c) {
            case '1' -> new Balloon(i, j, Sprite.BALLOON_RIGHT[0]);
            case '2' -> new Oneal(i, j, Sprite.ONEAL_RIGHT[0]);
            case '3' -> new Doll(i,j,Sprite.DOLL_RIGHT[0]);
            case '4' -> new Kondoria(i,j,Sprite.KONDORIA_RIGHT[0]);
            case '5' -> new Minvo(i,j,Sprite.MINVO_RIGHT[0]);
            case '6' -> new Ghost(i,j,Sprite.GHOST_RIGHT[0]);
        };
    }
}
