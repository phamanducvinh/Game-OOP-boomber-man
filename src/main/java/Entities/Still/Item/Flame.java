package Entities.Still.Item;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;

public class Flame extends Item{
    public Flame(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void effect(Bomber bomber) {
        if(bomber.getLengthFlame() < 4) {
            bomber.setLengthFlame(bomber.getLengthFlame() + 1);
        }
        destroy();
    }
}
