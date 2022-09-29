package Entities.Animate;

import Constants.Contants.DIRECTION;
import Entities.Entity;
import GameController.Bomberman;
import Graphics.Sprite;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AnimateEntity extends Entity {
    protected Sprite[] currentAnimate;
    protected int cntMove = 0;
    protected HashMap<DIRECTION,Sprite[]> animation = new HashMap<>();
    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }


    public void updateAnimation(int time) {
        this.sprite = Sprite.movingSprite(currentAnimate, time);
        this.img = this.sprite.getFxImage();
    }

    public abstract void update();
    public abstract void delete();
}
