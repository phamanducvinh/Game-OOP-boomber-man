package Entities.Still.Item;

import Entities.Animate.Brick;
import Entities.Animate.Character.Bomber;
import Entities.Still.Grass;
import Entities.Still.StillEntity;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

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
        if(hidden) {
            hidden = false;
            return;
        }
        gameMap.getItems().remove(this);
        gameMap.setTiles(tileX,tileY,new Grass(tileX,tileY,Sprite.grass));
    }

    abstract public void effect(Bomber bomber);
}
