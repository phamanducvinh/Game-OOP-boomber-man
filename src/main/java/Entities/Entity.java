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

    /**
     * Khởi tạo khối hình chữ nhật.
     * */
    public Rectangle2D getBoundary() {
        return new Rectangle2D(pixelY, pixelX,Sprite.SCALED_SIZE,Sprite.SCALED_SIZE);
    }

    // Kiểm tra xem có va chạm với thực thể khác không
    public boolean isCollision(Entity entity) {
        if(entity == null) return false;
        return getBoundary().intersects(entity.getBoundary());
    }

    // In ảnh ra màn hình
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(img, pixelY, pixelX);
    }

    // Hàm trừu tượng update thực thể sau mỗi milisecon
    public abstract void update();
}
