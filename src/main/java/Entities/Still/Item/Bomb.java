package Entities.Still.Item;

import Constants.Contants;
import Entities.Animate.AnimateEntity;
import Entities.Animate.Character.Bomber;
import Features.Explosion;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import static Constants.Contants.BOMB.BOMB;
import static Constants.Contants.BOMB.DESTROYED;

public class Bomb extends AnimateEntity {
    private final Bomber owner;
    private Explosion[][] explosion;
    public int timeBeforeExplode = 200;
    public boolean isExploded = false;

    public Bomb(int x, int y, Sprite sprite, Bomber bomber) {
        super(x, y, sprite);
        animation.put(BOMB, Sprite.BOMB);
        animation.put(DESTROYED, Sprite.EXPLODED);
        currentAnimate = animation.get(BOMB);
        owner = bomber;
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }
/*
    private void buildExplosion() {
        explosion = new Explosion[5][4];
        explosion[4][0] = new Explosion(tileX, tileY, Sprite.bomb_exploded, "CENTER");
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        for (int direction = 0; direction < 4; direction++) {
            for (int i = 0; i < Map.player_1.lengthBomb; i++) {
                int tileX = this.tileX + (i + 1) * dx[direction];
                int tileY = this.tileY + (i + 1) * dy[direction];
                if (tileX < 0 || tileX >= gameMap.WIDTH || tileY < 0 || tileY >= gameMap.HEIGHT) {
                    break;
                }
                Entity entity = gameMap.tiles[tileX][tileY];
                if (entity instanceof Wall) {
                    break;
                }
                if (direction <= 1) {
                    explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_vertical, "VERTICAL");
                } else {
                    explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_horizontal, "HORIZONTAL");
                }
                if (i == Map.player_1.lengthBomb - 1) {
                    switch (direction) {
                        case 0:
                            explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_vertical_down_last, "DOWN_LAST");
                            break;
                        case 1:
                            explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_vertical_top_last, "TOP_LAST");
                            break;
                        case 2:
                            explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_horizontal_right_last, "RIGHT_LAST");
                            break;
                        case 3:
                            explosion[direction][i] = new Explosion(tileX, tileY, Sprite.explosion_horizontal_left_last, "LEFT_LAST");
                            break;
                    }
                }
                if (entity instanceof Brick) {
                    break;
                }
            }
        }
    }

    public Bomber getOwner() {
        return owner;
    }

    @Override
    public void update() {
        updateAnimation();
        timeBeforeExplode -= 1;
        if (timeBeforeExplode == 0) {
            isExploded = true;
            destroy();
            buildExplosion();
        }
        if (timeBeforeExplode == -10) {
            delete();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, pixelX, pixelY);
        if (destroyed) {
            for (int direction = 0; direction < 4; direction++) {
                for (int i = 0; i < Map.player_1.lengthBomb; i++) {
                    if (explosion[direction][i] != null) {
                        explosion[direction][i].update();
                        explosion[direction][i].render(gc);
                    }
                }
            }
            explosion[4][0].update();
        }
    }

    @Override
    public void delete() {
        gameMap.bombs.remove(this);
        owner.countBombs -= 1;
    }
    */

}
