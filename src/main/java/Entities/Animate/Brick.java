package Entities.Animate;

import Constants.Contants;
import Entities.Animate.AnimateEntity;
import Entities.Still.Grass;
import Entities.Still.StillEntity;
import Graphics.Sprite;

import static Constants.Contants.DIRECTION.DESTROYED;
import static Graphics.Sprite.BRICK;
import static Graphics.Sprite.BRICK_EXPLODED;
import static Graphics.Sprite.GRASS;

public class Brick extends AnimateEntity {
    public Brick(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        setBlock(true);
        setDestroyable(true);
        animation.put(DESTROYED,BRICK_EXPLODED);
        gameMap.setTiles(x,y,this);
    }

    @Override
    public void update() {
    }

    @Override
    public void delete() {
        Grass grass = new Grass(tileX,tileY,GRASS);
        gameMap.setTiles(tileX,tileY,grass);
    }
}
