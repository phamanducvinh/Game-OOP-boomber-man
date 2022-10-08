package Entities.Animate;

import Constants.Contants.DIRECTION;
import Entities.Entity;
import GameController.Bomberman;
import Graphics.Sprite;
import javafx.util.Pair;

import java.util.HashMap;

import static Constants.Contants.BOMB_STATUS.DESTROYED;



public abstract class AnimateEntity extends Entity {
    public HashMap<Enum,Sprite[]> animation = new HashMap<>();
    protected Sprite[] currentAnimate;
    protected int timeDestroyed;
    protected int cntMove = 0;
    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void updateAnimation() {
        long time = Bomberman.time;
        this.sprite = Sprite.movingSprite(currentAnimate, 3,time);
        this.img = this.sprite.getFxImage();
    }

    public void destroy() {
        currentAnimate = animation.get(DESTROYED);
        timeDestroyed = 20;
        destroyed = true;
    }

    public void updateDestroyAnimation() {
        currentAnimate = animation.get(DESTROYED);
        if (timeDestroyed -- >= 0) {
            updateDestroyAnimation();
        }else {
            delete();
        }
    }

    public abstract void delete();
}
