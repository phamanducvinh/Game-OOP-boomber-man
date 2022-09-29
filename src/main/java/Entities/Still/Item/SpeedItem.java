package Entities.Still.Item;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        gameMap.addItem(this);
    }


    @Override
    public void effect(Bomber player) {
        player.setSpeed(player.getSpeed()+1);
        remove();
    }
}
