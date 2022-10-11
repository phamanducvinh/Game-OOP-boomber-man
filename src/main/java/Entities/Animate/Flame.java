package Entities.Animate;

import Constants.Constants;
import Entities.Animate.Character.Character;
import Entities.Entity;
import Entities.Still.Item.Item;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import static Constants.Constants.EXPLOSION.*;


public class Flame extends AnimateEntity{
    private boolean destroyBrick;
    public Flame(int x, int y, Sprite sprite, Constants.EXPLOSION status) {
        super(x, y, sprite);
        animation.put(HORIZONTAL,Sprite.HORIZONTAL);
        animation.put(RIGHT_LAST,Sprite.RIGHT_LAST);
        animation.put(LEFT_LAST,Sprite.LEFT_LAST);
        animation.put(VERTICAL,Sprite.VERTICAL);
        animation.put(TOP_LAST,Sprite.TOP_LAST);
        animation.put(DOWN_LAST,Sprite.DOWN_LAST);
        animation.put(CENTER,Sprite.EXPLODED);
        currentAnimate = animation.get(status);
        destroyBrick = false;
        timeDestroyed = 20;
    }

    @Override
    public void delete() {
        isCollision();
        gameMap.getFlames().remove(this);
    }

    @Override
    public void update() {
        if(timeDestroyed -- >= 0) {
            updateAnimation();
        } else {
            delete();
        }
    }

    private void isCollision() {
        Entity entity = gameMap.getTiles(tileX,tileY);
        if (entity instanceof Brick) {
            destroyBrick = true;
            System.out.println(1);
            ((Brick) entity).destroy();
        }
        for (Character character: gameMap.getCharacters()) {
            if (this.isCollision(character)) {
                character.destroy();
            }
        }
        for (Item item: gameMap.getItems()) {
            if (this.isCollision(item) && item.isDestroyable()) {
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
