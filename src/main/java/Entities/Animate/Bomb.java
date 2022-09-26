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
        currentAnimate = new Sprite[]{Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2};
    }

    @Override
    public void update() {
        cntMove = (cntMove + 1) % 9;
        updateAnimation(cntMove / 3);
        timeBeforeExplode--;
    }
}
