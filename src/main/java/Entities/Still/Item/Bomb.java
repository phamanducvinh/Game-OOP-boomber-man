package Entities.Still.Item;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;

public class Bomb extends Item{
    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void effect(Bomber bomber) {
        if(bomber.getMaxBombs() < 5) {
            bomber.setMaxBombs(bomber.getMaxBombs() + 1);
        }
        destroy();
    }
}
