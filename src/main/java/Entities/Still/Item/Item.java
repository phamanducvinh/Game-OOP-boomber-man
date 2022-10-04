package Entities.Still.Item;

import Entities.Animate.Character.Bomber;
import Entities.Still.Grass;
import Entities.Still.StillEntity;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class Item extends StillEntity {
    private boolean hidden;
    public Item(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        hidden = true;
    }

    public void destroy() {
        if(hidden) {
            hidden = false;
        }
        gameMap.removeItem(this);
        gameMap.setTiles(tileX,tileY
        ,new Grass(tileX,tileY,Sprite.grass));
    }

    abstract public void effect(Bomber bomber);
}
