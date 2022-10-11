package Entities.Animate;

import Entities.Animate.Character.Bomber;
import Entities.Entity;
import Entities.Still.Wall;
import Graphics.Sprite;
import Map.Map;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static Constants.Constants.*;
import static Constants.Constants.BOMB_STATUS.*;
import static Constants.Constants.EXPLOSION.*;


@Getter
@Setter
public class Bomb extends AnimateEntity {
    private final Bomber owner;
    private int timeBeforeExplode;
    private Explosion[][] explosion;
    private boolean exploded ;

    public Bomb(int x, int y, Sprite sprite, Bomber owner) {
        super(x, y, sprite);
        this.owner = owner;
        animation.put(COUNTDOWN,Sprite.BOMB);
        animation.put(DESTROYED,Sprite.EXPLODED);
        timeBeforeExplode = 90;
        currentAnimate = animation.get(COUNTDOWN);
    }

    private void buildExplosion() {
        explosion =  new Explosion[5][4];
        explosion[4][0] = new Explosion(tileX,tileY,Sprite.BOMB[0],CENTER);
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        for (int direction = 0; direction < 4; direction++) {
            for (int i = 0; i < gameMap.getPlayer().getLengthFlame(); i++) {
                int tileX = this.tileX + (i + 1) * dx[direction];
                int tileY = this.tileY + (i + 1) * dy[direction];
                if (tileX < 0 || tileX >= HEIGHT || tileY < 0 || tileY >= WIDTH) {
                    break;
                }
                Entity entity = gameMap.getEntity()[tileX][tileY];
                if (entity instanceof Wall) {
                    break;
                }

                if (direction <= 1) {
                    explosion[direction][i] = new Explosion(tileX, tileY, Sprite.VERTICAL[0], VERTICAL);
                } else {
                    explosion[direction][i] = new Explosion(tileX, tileY, Sprite.HORIZONTAL[0], HORIZONTAL);
                }
                if (i == owner.getLengthFlame() - 1) {
                    switch (direction) {
                        case 2 -> explosion[direction][i] = new Explosion(tileX, tileY, Sprite.DOWN_LAST[0], DOWN_LAST);
                        case 3 -> explosion[direction][i] = new Explosion(tileX, tileY, Sprite.TOP_LAST[0], TOP_LAST);
                        case 0 -> explosion[direction][i] = new Explosion(tileX, tileY, Sprite.RIGHT_LAST[0], RIGHT_LAST);
                        case 1 -> explosion[direction][i] = new Explosion(tileX, tileY, Sprite.LEFT_LAST[0], LEFT_LAST);
                    }
                }
                if (entity instanceof Brick) {
                    break;
                }
            }
        }

    }

    @Override
    public void update() {
        updateAnimation();
        timeBeforeExplode --;

        if (timeBeforeExplode == 0) {
            exploded = true;
            destroy();
            //buildExplosion();
        }

        if(timeBeforeExplode == -10) {
            delete();
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(img, pixelY, pixelX);
        if (destroyed) {

            /*
            for (int direction = 0; direction < 4; direction++) {
                for (int i = 0; i < owner.getLengthFlame(); i++) {
                    if (explosion[direction][i] != null) {
                        explosion[direction][i].update();
                        explosion[direction][i].render(graphicsContext);
                    }
                }
            }
            explosion[4][0].update();

             */
        }
    }

    @Override
    public void delete() {
        gameMap.getBombs().remove(this);
        owner.explosionBomb();
    }
}
