package Entities.Still.Item;

import Entities.Animate.Brick;
import Entities.Animate.Character.Bomber;
import Entities.Still.Grass;
import Entities.Still.StillEntity;
import GameController.SoundController;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

import static Constants.Constants.SOUND_PATH;

@Getter
@Setter
public abstract class Item extends StillEntity {
    protected boolean hidden;
    public Item(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        destroyable = true;
        hidden = true;
        gameMap.setTiles(x,y,new Brick(x,y, Sprite.brick));
        gameMap.getItems().add(this);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if(!hidden) {
            graphicsContext.drawImage(img,pixelY,pixelX);
        }
    }

    public void destroy() {
        if (gameMap.getTiles()[tileX][tileY] instanceof Grass) {
                gameMap.getItems().remove(this);
                SoundController.playEffectSound(SOUND_PATH[4]);
        }
        else {
                ((Brick) gameMap.getTiles()[tileX][tileY]).destroy();
        }
    }

    @Override
    public void update(){
        if(gameMap.getTiles()[tileX][tileY] instanceof Grass) {
            hidden = false;
        }
    }

    abstract public void effect(Bomber bomber);
}
