package Entities.Still.Item;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;

public class BombItem extends Item {
    public BombItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void effect(Bomber player) {
        int maxBomb = player.getMaxBomb();
        player.setMaxBomb(maxBomb+1);
        destroy();
    }
}
