package Factory;

import Entities.Still.Item.BombItem;
import Entities.Still.Item.FlameItem;
import Entities.Still.Item.SpeedItem;

import static Graphics.Sprite.*;

public class ItemFactory {
    public static void getItem(char c,int i,int j) {
        switch (c){
            case 's' -> new SpeedItem(i,j, SPEED_ITEM);
            case 'b' -> new BombItem(i,j,BOMB_ITEM);
            case 'f' -> new FlameItem(i,j,FLAME_ITEM);
        }
    }
}
