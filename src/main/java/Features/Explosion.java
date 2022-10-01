package Features;

import Constants.Contants;
import Entities.Animate.AnimateEntity;
import Entities.Animate.Brick;
import Entities.Animate.Character.Character;
import Entities.Entity;
import Entities.Still.Item.Bomb;
import Entities.Still.Item.Item;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;
import java.util.List;

import static Constants.Contants.EXPLOSION.*;

public class Explosion extends AnimateEntity {
    boolean destroyBrick = false;

    public Explosion(int x, int y, Sprite sprite, Contants.EXPLOSION animate) {
        super(x, y, sprite);
        animation.put(HORIZONTAL,Sprite.HORIZONTAL);
        animation.put(RIGHT_LAST,Sprite.RIGHT_LAST);
        animation.put(VERTICAL,Sprite.VERTICAL);
        animation.put(LEFT_LAST,Sprite.LEFT_LAST);
        animation.put(TOP_LAST,Sprite.TOP_LAST);
        animation.put(DOWN_LAST,Sprite.DOWN_LAST);
        animation.put(CENTER,Sprite.EXPLODED);
        currentAnimate = animation.get(animate);
    }

    @Override
    public void delete() {
        if (!destroyed) {
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
        Entity entity = gameMap.getTiles(tileX,tileY);
        if (entity instanceof Brick) {
            destroyBrick = true;
            ((Brick) entity).destroy();
        }

        List<Character> characters = gameMap.getCharacters();
        for (Character character: characters) {
            if (isCollision(character)) {
                character.destroy();
            }
        }

        List<Item> items = gameMap.getItems();
        for (Item item: items) {
            if (isCollision(item)) {
                item.destroy();
            }
        }


        for (Bomb bomb: gameMap.bombs) {
            if (this.isCollision(bomb) && !bomb.exploded) {
                bomb.timeBeforeExplode = 1;
            }
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if (!destroyBrick) {
            super.render(graphicsContext);
        }
    }
}
