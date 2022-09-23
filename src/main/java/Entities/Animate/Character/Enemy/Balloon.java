package Entities.Animate.Character.Enemy;

import Constants.Contants.DIRECTION.*;
import Graphics.Sprite;

import static Constants.Contants.DIRECTION.*;
import static Graphics.Sprite.*;


public class Balloon extends Enemy {
    public Balloon(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        animation.put(LEFT,BALLOON_LEFT);
        animation.put(UP,BALLOON_LEFT);
        animation.put(RIGHT,BALLOON_RIGHT);
        animation.put(DOWN,BALLOON_RIGHT);
        animation.put(DESTROYED,BALLOON_DEAD);
        currentAnimate = animation.get(RIGHT);
    }
}
