package Factory;

import Entities.Animate.Character.Bomber;
import Entities.Still.Item.*;
import Graphics.Sprite;

public class ItemFactory {
    public static void getItem(char c,int i,int j) {
         switch (c) {
            case 'b' -> new Bomb(i,j, Sprite.BOMB_ITEM);
            case 's' -> new Speed(i,j,Sprite.SPEED_ITEM);
            case 'f' -> new Flame(i,j,Sprite.FLAME_ITEM);
            case 'x' -> new Portal(i,j,Sprite.PORTAL);
            case 'w' -> new WallPass(i,j, Sprite.WALL_PASS_ITEM);
            case 'l' -> new BombPass(i,j,Sprite.BOMB_PASS_ITEM);
            case 'k' -> new FlamePass(i,j, Sprite.FLAME_PASS_ITEM);
            default -> {}
        };
    }
}
