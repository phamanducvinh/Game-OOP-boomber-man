package Entities.Animate.Character.Enemy;

import Constants.Contants.DIRECTION;
import Graphics.Sprite;

public class Balloon extends Enemy {
    public Balloon(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        animation.put(DIRECTION.LEFT, Sprite.BALLOON_LEFT);
        animation.put(DIRECTION.UP,Sprite.BALLOON_LEFT);
        animation.put(DIRECTION.RIGHT,Sprite.BALLOON_RIGHT);
        animation.put(DIRECTION.DOWN,Sprite.BALLOON_RIGHT);
        animation.put(DIRECTION.DESTROYED,Sprite.BALLOON_DEAD);
        currentAnimate = animation.get(DIRECTION.RIGHT);
    }
}
