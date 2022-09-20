package Entities;
import Graphics.Sprite;
import Map.Map;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    public int pixelX, pixelY;
    public int tileX, tileY;
    protected Sprite sprite;
    protected Image img;
    public boolean canBlock = false;

    public Entity(int x, int y, Sprite sprite) {
        this.tileX = x;
        this.tileY = y;
        this.pixelX = x * Sprite.SCALED_SIZE;
        this.pixelY = y * Sprite.SCALED_SIZE;
        this.sprite = sprite;
        this.img = sprite.getFxImage();
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(pixelX, pixelY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(img, pixelX, pixelY);
    }

    public abstract void update();
}
