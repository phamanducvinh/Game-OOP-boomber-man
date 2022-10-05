package Entities.Animate;

import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Explosion extends AnimateEntity{
    private boolean destroyBrick;

    public Explosion(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if(!destroyBrick) {
            super.render(graphicsContext);
        }
    }
}
