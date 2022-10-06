package Factory;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Input.PlayerInput;

public class PlayerFactory {
    public static Bomber getPlayer(char c, int x, int y) {
        if(c == 'p') {
            return new Bomber(x,y, Sprite.PLAYER_RIGHT[0],new PlayerInput());
        }
        return null;
    }
}
