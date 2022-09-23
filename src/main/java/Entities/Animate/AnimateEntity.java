package Entities.Animate;

import Constants.Contants.DIRECTION;
import Entities.Entity;
import GameController.Bomberman;
import Graphics.Sprite;
import java.util.HashMap;

public abstract class AnimateEntity extends Entity {
    protected Sprite[] currentAnimate;
    protected int cntMove = 0;
    public static HashMap<DIRECTION,Sprite[]> animation = new HashMap<>();
    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public abstract void getDirection();

    public void updateAnimation(int time) {
        this.sprite = Sprite.movingSprite(currentAnimate, time);
        this.img = this.sprite.getFxImage();
    }

    public abstract void update();
}
