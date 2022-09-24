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

    //private KeyInput keyInput;
    private int DIRECT_X[] = new int[]{0, 1, 0, 1};
    private int DIRECT_Y[] = new int[]{1, 0, 1, 0};

    public Bomber(int x, int y, Sprite sprite) {
        super( x, y, sprite);
        animation.put(Contants.DIRECTION.LEFT, Sprite.PLAYER_LEFT);
        animation.put(Contants.DIRECTION.RIGHT, Sprite.PLAYER_RIGHT);
        animation.put(Contants.DIRECTION.UP, Sprite.PLAYER_UP);
        animation.put(Contants.DIRECTION.DOWN,Sprite.PLAYER_DOWN);
        currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
        //this.keyInput = keyInput;
    }

    @Override
    public void update() {

        if(cntMove==0) {
            cntMove = Sprite.SCALED_SIZE-1;
        } else cntMove--;

        for(int i=0;i<2;++i) {
            updateAnimation(cntMove);
            move();
        }
    }

    @Override
    public void getDirection() {

    }

    public void getDirection(String code) {
        if(code == null) {
            System.out.println("null");
            return;
        }
        switch (code){
            case ("W"):
                this.setVelocity(-defaultVelocity, 0);
                currentAnimate = animation.get(Contants.DIRECTION.UP);
                direction = Contants.DIRECTION.UP;
                break;
            case ("A"):
                this.setVelocity(0, -defaultVelocity);
                currentAnimate = animation.get(Contants.DIRECTION.LEFT);
                direction = Contants.DIRECTION.LEFT;
                break;
            case ("S"):
                this.setVelocity(defaultVelocity, 0);
                currentAnimate = animation.get(Contants.DIRECTION.DOWN);
                direction = Contants.DIRECTION.DOWN;
                break;
            case ("D"):
                this.setVelocity(0, defaultVelocity);
                currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
                direction = Contants.DIRECTION.RIGHT;
                break;
            default:
                break;
        }

        //this.setVelocity(0, 0);
        //Contants.DIRECTION newDirection = keyInput.handleKeyInput(this);

        /*
        if (newDirection == Contants.DIRECTION.UP){
            this.setVelocity(-defaultVelocity, 0);
            currentAnimate = animation.get(Contants.DIRECTION.UP);
            direction = newDirection;
            //updateAnimation(1);
        }
        if (newDirection == Contants.DIRECTION.RIGHT){
            this.setVelocity(0, defaultVelocity);
            currentAnimate = animation.get(Contants.DIRECTION.RIGHT);
            direction = newDirection;
            //updateAnimation(1);
        }
        if (newDirection == Contants.DIRECTION.DOWN){
            this.setVelocity(defaultVelocity, 0);
            currentAnimate = animation.get(Contants.DIRECTION.DOWN);
            direction = newDirection;
            //updateAnimation(1);
        }
        if (newDirection == Contants.DIRECTION.LEFT){
            this.setVelocity(0, -defaultVelocity);
            currentAnimate = animation.get(Contants.DIRECTION.LEFT);
            direction = newDirection;
            //updateAnimation(3);
        }*/

    }
}