package Entities.Animate;

import Graphics.Sprite;

public class Brick extends AnimateEntity{
    public Brick(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        canBlock = true;
    }

    @Override
    public void update() {

    }
}
