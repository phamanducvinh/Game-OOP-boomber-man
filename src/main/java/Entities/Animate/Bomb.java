package Entities.Animate;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;

public class Bomb extends AnimateEntity {
    private final Bomber owner;
    private int timeBeforeExplode;


    public Bomb(int x, int y, Sprite sprite, Bomber owner) {
        super(x, y, sprite);
        this.owner = owner;
        timeBeforeExplode = 100;
        currentAnimate = Sprite.BOMB;
    }

    @Override
    public void update() {
        cntMove = (cntMove + 1) % 9;
        updateAnimation();
        timeBeforeExplode--;
    }
}
