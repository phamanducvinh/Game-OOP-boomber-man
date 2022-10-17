package Entities.Animate.Character;
import Entities.Animate.AnimateEntity;
import Entities.Animate.Bomb;
import Entities.Animate.Brick;
import Entities.Animate.Character.Enemy.Enemy;
import Entities.Entity;
import Features.Movable;
import Graphics.Sprite;
import javafx.geometry.Rectangle2D;
import lombok.Getter;
import lombok.Setter;

import static Constants.Constants.*;

@Getter
@Setter
public abstract class Character extends AnimateEntity implements Movable {

    protected int defaultVelocity;
    protected int velocityX;
    protected int velocityY;
    protected DIRECTION direction;
    protected int speed;
    protected boolean collision;
    protected boolean stand;
    protected boolean wallPass;
    protected boolean bombPass;
    protected boolean flamePass;

    public Character(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        gameMap.getCharacters().add(this);
        defaultVelocity = 1;
        velocityX = 0;
        velocityY = 0;
        speed = 1;

        stand = true;
        collision = false;
        wallPass = false;
        bombPass = false;
        flamePass = false;
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void addVelocity(int velocityX, int velocityY) {
        this.velocityX += velocityX;
        this.velocityY += velocityY;
    }

    public void move() {
        pixelX += velocityX;
        pixelY += velocityY;
        tileX = pixelX / Sprite.SCALED_SIZE;
        tileY = pixelY / Sprite.SCALED_SIZE;
    }

    public void checkCollision() {
        collision = false;
        pixelX += this.velocityX;
        pixelY += this.velocityY;
        for (Bomb bomb : gameMap.getBombs()) {
            if (this.isCollision(bomb)) {
                if (bomb.isDestroyed() && !this.flamePass) {
                    this.destroy();
                } else {
                    if (isBombPass()) {
                        continue;
                    }
                    collision = true;
                }
                if (bomb.getOwner() == this && !bomb.isBlock()) {
                    collision = false;
                }
            } else {
                if (bomb.getOwner() == this) {
                    bomb.setBlock(true);
                }
            }
        }

        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                Entity entity = gameMap.getTiles()[i][j];

                if (entity instanceof Brick && isWallPass()) {
                    continue;
                }

                if (entity.isBlock() && this.isCollision(entity)) {
                    collision = true;
                }
            }
        }

        stand = collision || (velocityX == 0 && velocityY == 0);
        pixelX -= this.velocityX;
        pixelY -= this.velocityY;
    }

    @Override
    public void update() {
        if (isDestroyed()) {
            updateDestroyAnimation();
            return;
        }

        if(this instanceof Bomber) {
            for(int i=0;i<gameMap.getCharacters().size();++i) {
                Character character = gameMap.getCharacters().get(i);
                if(character instanceof Enemy && gameMap.getPlayer().isCollision2(character)) {
                    this.destroy();
                    break;
                }
            };
        }

        for (int i = 0; i < speed; ++i) {
            findDirection();
            checkCollision();
            if (!stand || this instanceof Enemy) {
                updateAnimation();
            }
            if (!collision) {
                move();
            }
        }
    }

    public abstract void findDirection();
}
