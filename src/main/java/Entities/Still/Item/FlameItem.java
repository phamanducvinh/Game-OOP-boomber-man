package Entities.Still.Item;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;

public class FlameItem extends Item{
    public FlameItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void effect(Bomber bomber) {
        int flameLength = bomber.getFlameLength();
        bomber.setFlameLength(flameLength+1);
        destroy();
    }
}
