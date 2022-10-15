package Entities.Still.Item;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
public class FlamePass extends Item {
    public FlamePass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void effect(Bomber bomber) {
        bomber.setFlamePass(true);
        destroy();
    }
}
