package Entities.Animate.Character.Enemy;

import static Constants.Constants.DIRECTION;
import static Constants.Constants.DIRECTION.*;

import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;

public class Fire extends Enemy{
    public Fire(int x, int y, Sprite sprite,DIRECTION direction) {
        super(x,y,sprite);
        this.direction = direction;
        animation.put(LEFT,Sprite.fire_left);
        animation.put(UP,Sprite.fire_up);
        animation.put(DOWN,Sprite.fire_down);
        animation.put(RIGHT,Sprite.fire_right);
        animation.put(DESTROYED,new Sprite[] {
                Sprite.mob_dead1,
                Sprite.mob_dead2,
                Sprite.mob_dead3
        });
        speed = 2 ;
        wallPass = true;
        bombPass = true;
    }

    @Override
    public DIRECTION trace(Bomber bomber, Enemy enemy, Map gameMap) {
        return this.direction;
    }

    @Override
    public void delete() {
        gameMap.getCharacters().remove(this);
    }

    @Override
    public void update() {
        for (int i = 1; i <= speed; i++) {
            findDirection();
            checkCollision();
            if (!isStand()) {
                updateAnimation();
            }
            if (isCollision() || isStand()) {
                delete();
            }
            move();
        }
    }
}
