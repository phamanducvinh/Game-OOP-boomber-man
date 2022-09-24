package Entities.Animate.Character;
import Constants.Contants;
import Entities.Entity;
import Graphics.Sprite;
import Input.KeyInput;
import Input.PlayerOne;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Bomber extends Character {
    private KeyInput keyInput;
    private int DIRECT_X[] = new int[]{0, 1, 0, 1};
    private int DIRECT_Y[] = new int[]{1, 0, 1, 0};

    public Bomber(int x, int y, Sprite sprite, KeyInput keyInput) {
        super( x, y, sprite);
        animation.put(Contants.DIRECTION.LEFT,
                new Sprite[]{Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2});
        animation.put(Contants.DIRECTION.RIGHT,
                new Sprite[]{Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2});
        animation.put(Contants.DIRECTION.UP,
                new Sprite[]{Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2});
        animation.put(Contants.DIRECTION.DOWN,
                new Sprite[]{Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2});
        currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
        speed = 2;
        this.keyInput = keyInput;
    }

    @Override
    public void update() {

    }

    @Override
    public void getDirection() {
        this.setVelocity(0, 0);
        Contants.DIRECTION newDirection = keyInput.handleKeyInput(this);
        if (newDirection == Contants.DIRECTION.UP){
            this.setVelocity(-defaultVelocity, 0);
            currentAnimate = animation.get(Contants.DIRECTION.UP);
            direction = newDirection;
            updateAnimation();
        }
        if (newDirection == Contants.DIRECTION.RIGHT){
            this.setVelocity(0, defaultVelocity);
            currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
            direction = newDirection;
            updateAnimation();
        }
        if (newDirection == Contants.DIRECTION.DOWN){
            this.setVelocity(defaultVelocity, 0);
            currentAnimate = animation.get(Contants.DIRECTION.DOWN);
            direction = newDirection;
            updateAnimation();
        }
        if (newDirection == Contants.DIRECTION.LEFT){
            this.setVelocity(0, -defaultVelocity);
            currentAnimate = animation.get(Contants.DIRECTION.LEFT);
            direction = newDirection;
            updateAnimation();
        }

    }
}