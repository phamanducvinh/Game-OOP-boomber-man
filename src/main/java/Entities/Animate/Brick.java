package Entities.Animate;

import Entities.Animate.AnimateEntity;
import Entities.Still.Grass;
import Entities.Still.StillEntity;
import Graphics.Sprite;

import static Constants.Contants.DIRECTION.DESTROYED;

public class Brick extends AnimateEntity {
    public Brick(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        block = true;
        destroyable = true;
        animation.put(DESTROYED,Sprite.BRICK_EXPLODED);
        gameMap.setTiles(x,y,this);
    }

    @Override
    public void delete() {
        Grass grass = new Grass(this.tileX, this.tileY, Sprite.grass);
        gameMap.setTiles(tileX,tileY,grass);
    }

    @Override
    public void update() {
        if(destroyed) {
            updateDestroyAnimation();
        }
    }

}
