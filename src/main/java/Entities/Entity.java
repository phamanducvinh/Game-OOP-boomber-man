package Entities;
import Graphics.Sprite;
import Map.Map;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;

public abstract class Entity {
    protected static Map gameMap;
    protected int pixelX, pixelY;
    protected int tileX, tileY;
    protected Sprite sprite;
    protected Image img;
    public boolean canBlock = false;

    public static void setGameMap(Map gameMap) {
        Entity.gameMap = gameMap;
    }

    public Entity(int x, int y, Sprite sprite) {
        this.tileX = x;
        this.tileY = y;
        this.pixelX = x * Sprite.SCALED_SIZE;
        this.pixelY = y * Sprite.SCALED_SIZE;
        this.sprite = sprite;
        this.img = sprite.getFxImage();
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(pixelY, pixelX,Sprite.SCALED_SIZE,Sprite.SCALED_SIZE);
    }

    public boolean isCollision(Entity entity) {
        if(entity == null) return false;
        return getBoundary().intersects(entity.getBoundary());
    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(img, pixelY, pixelX);
    }

    public abstract void update();

    public Pair<Integer,Integer> getTile() {
        return new Pair(tileX,tileY);
    }

}
