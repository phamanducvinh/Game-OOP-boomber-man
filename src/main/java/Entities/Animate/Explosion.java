package Entities.Animate;

import Constants.Contants;
import Entities.Animate.Character.Character;
import Entities.Entity;
import Entities.Still.Item.Item;
import Entities.Still.StillEntity;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import static Constants.Contants.EXPLOSION.*;

public class Explosion extends AnimateEntity{
    private boolean destroyBrick;

    public Explosion(int x, int y, Sprite sprite, Contants.EXPLOSION status) {
        super(x, y, sprite);
        animation.put(HORIZONTAL,Sprite.HORIZONTAL);
        animation.put(RIGHT_LAST,Sprite.RIGHT_LAST);
        animation.put(VERTICAL,Sprite.VERTICAL);
        animation.put(TOP_LAST,Sprite.TOP_LAST);
        animation.put(DOWN_LAST,Sprite.DOWN_LAST);
        animation.put(CENTER,Sprite.BOMB);
        currentAnimate = animation.get(status);
    }

    @Override
    public void delete() {
        if(!destroyed) {
            destroyed = true;
            isCollision();
        }
    }

    @Override
    public void update() {
        updateAnimation();
        delete();
    }

    private void isCollision() {
        Entity entity = gameMap.getEntity(tileX,tileY);
        if (entity instanceof Brick) {
            destroyBrick = true;
            ((Brick) entity).destroy();
        }
        for (Character character: gameMap.getCharacters()) {
            if (this.isCollision(character)) {
                character.destroy();
            }
        }
        for (Item item: gameMap.getItems()) {
            if (this.isCollision(item)) {
                item.destroy();
            }
        }


        for (Bomb bomb: gameMap.getBombs()) {
            if (this.isCollision(bomb) && !bomb.isDestroyed()) {
                bomb.setTimeBeforeExplode(1);
            }
        }

    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if(!destroyBrick) {
            super.render(graphicsContext);
        }
    }
}
