package Entities.Animate;

import Constants.Contants.DIRECTION;
import Entities.Entity;
import GameController.Bomberman;
import Graphics.Sprite;
import java.util.HashMap;

public abstract class AnimateEntity extends Entity {
    protected Sprite[] currentAnimate;


    public HashMap<DIRECTION,Sprite[]> animation = new HashMap<>();
    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public abstract void getDirection();

    public void updateAnimation() {
        this.sprite = Sprite.movingSprite(currentAnimate,3, Bomberman.time);
        this.img = this.sprite.getFxImage();
    }

    public abstract void update();
}
