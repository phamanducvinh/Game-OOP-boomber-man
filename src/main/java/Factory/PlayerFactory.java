package Factory;

import Entities.Animate.Character.Bomber;
import Entities.Entity;
import Graphics.Sprite;
import Input.PlayerOneKeyInput;

public class PlayerFactory {
    public Entity getPlayer(char c,int i,int j) {
        switch (c) {
            case 'p' :
                return new Bomber(i,j, Sprite.PLAYER_RIGHT[0], new PlayerOneKeyInput());
            default:
                return null;
        }
    }
}
