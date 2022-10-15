package Entities.Animate;

import Constants.Constants;
import Entities.Animate.Character.Character;
import Entities.Entity;
import Entities.Still.Item.Item;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

import static Constants.Constants.EXPLOSION.*;

@Getter
@Setter
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
        timeDestroyed = 20;
        destroyBrick = false;
        Entity entity = gameMap.getTiles(tileX,tileY);
        if (entity instanceof Brick) {
            destroyBrick = true;
            //((Brick) entity).destroy();
        }
    }

    @Override
    public void delete() {
        gameMap.getFlames().remove(this);
        isCollision();
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
            ((Brick) entity).destroy();
        }
        for (Character character: gameMap.getCharacters()) {
            if (this.isCollision(character) && !character.isFlamePass()) {
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
        if(destroyBrick) {
            return;
        }
        super.render(graphicsContext);
    }
}
