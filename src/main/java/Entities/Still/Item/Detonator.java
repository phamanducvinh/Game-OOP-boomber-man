package Entities.Still.Item;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;

public class Detonator extends Item{
    public Detonator(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void effect(Bomber bomber) {
        bomber.setLife(bomber.getLife()+1);
        destroy();
    }
}
