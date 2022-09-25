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
    public HashMap<DIRECTION,Sprite[]> animation = new HashMap<>();
    //public HashMap<DIRECTION,Sprite[]> animationPlayerOne = new HashMap<>();
    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public abstract void getDirection();
    public ArrayList<Pair<Integer, Integer>> getBorder(){
        int width = (int) currentAnimate[0].getFxImage().getWidth();
        int height = (int) currentAnimate[0].getFxImage().getHeight();
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        result.add(new Pair<>(pixelX, pixelY));
        result.add(new Pair<>(pixelX + height - 1, pixelY));
        result.add(new Pair<>(pixelX, pixelY + width - 1));
        result.add(new Pair<>(pixelX + height - 1, pixelY + width - 1));
        return result;
    }
    public void updateAnimation(int time) {
        this.sprite = Sprite.movingSprite(currentAnimate, time);
        this.img = this.sprite.getFxImage();
    }

    public abstract void update();
}
