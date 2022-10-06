package Entities.Still.Item;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;

public class Speed extends Item{
    public Speed(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void effect(Bomber bomber) {
        bomber.setSpeed(bomber.getSpeed() + 1);
        destroy();
    }
}
