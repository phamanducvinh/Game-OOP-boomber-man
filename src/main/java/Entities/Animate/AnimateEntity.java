package Entities.Animate;
import Entities.Entity;
import GameController.Bomberman;
import Graphics.Sprite;
import java.util.HashMap;

import static Constants.Constants.DIRECTION.DESTROYED;

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
        timeDestroyed = 50;
        destroyed = true;
    }

    public void updateDestroyAnimation() {
        if (timeDestroyed -- >= 0) {
            updateAnimation();
        }else {
            delete();
        }
    }

    public abstract void delete();
}
