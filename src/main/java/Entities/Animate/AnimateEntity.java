package Entities.Animate;

import Constants.Contants.DIRECTION;
import Entities.Entity;
import GameController.Bomberman;
import Graphics.Sprite;
import java.util.HashMap;

import static Constants.Contants.DIRECTION.DESTROYED;

public abstract class AnimateEntity extends Entity {
    protected Sprite[] currentAnimate;
    protected int timeDestroyed;
    protected HashMap<Enum,Sprite[]> animation = new HashMap<>();
    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }


    public void updateAnimation() {
        this.sprite = Sprite.movingSprite(currentAnimate, 3,Bomberman.getSystemTime());
        this.img = this.sprite.getFxImage();
    }

    public abstract void delete();

    public void destroy() {
        currentAnimate = animation.get(DESTROYED);
        setDestroyed(false);
    }

    public void updateDestroyAnimation() {
        if(timeDestroyed-- >= 0) {
            updateAnimation();
        }else {
            delete();
        }
    }
}
