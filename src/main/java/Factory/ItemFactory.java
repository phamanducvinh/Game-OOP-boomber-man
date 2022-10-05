package Factory;

import Entities.Animate.Character.Bomber;
import Entities.Still.Item.Bomb;
import Entities.Still.Item.Flame;
import Entities.Still.Item.Item;
import Entities.Still.Item.Portal;
import Entities.Still.Item.Speed;
import Graphics.Sprite;

public class ItemFactory {
    public static Item getItem(char c,int i,int j) {
        return switch (c) {
            case 'b' ->  new Bomb(i,j, Sprite.BOMB_ITEM);
            case 's' -> new Speed(i,j,Sprite.SPEED_ITEM);
            case 'f' -> new Flame(i,j,Sprite.FLAME_ITEM);
            case 'x' -> new Portal(i,j,Sprite.PORTAL);
            default -> null;
        };
    }
}
