package Entities.Still;

import Entities.Animate.AnimateEntity;
import Graphics.Sprite;

public class Brick extends StillEntity {
    public Brick(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        block = true;
    }

    @Override
    public void update() {

    }

}
