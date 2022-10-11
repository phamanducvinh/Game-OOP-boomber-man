package Entities.Animate;

import Entities.Animate.Character.Bomber;
import Entities.Entity;
import Entities.Still.Wall;
import GameController.SoundController;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import static Constants.Constants.*;
import static Constants.Constants.BOMB_STATUS.*;
import static Constants.Constants.DIRECTION.DESTROYED;
import static Constants.Constants.EXPLOSION.*;


@Getter
@Setter
public class Bomb extends AnimateEntity {
    private final Bomber owner;
    private int timeBeforeExplode;

    public Bomb(int x, int y, Sprite sprite, Bomber owner) {
        super(x, y, sprite);
        animation.put(COUNTDOWN, Sprite.BOMB);
        animation.put(DESTROYED, Sprite.EXPLODED);
        this.timeBeforeExplode = 180;
        this.owner = owner;
        this.currentAnimate = animation.get(COUNTDOWN);
    }

    private void buildExplosion() {
        gameMap.getFlames().add(new Flame(tileX, tileY, Sprite.BOMB[0], CENTER));
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        for (int direction = 0; direction < 4; direction++) {
            for (int i = 1; i <= owner.getLengthFlame(); i++) {
                int tileX = this.tileX + i * dx[direction];
                int tileY = this.tileY + i * dy[direction];
                if (tileX < 0 || tileX >= HEIGHT || tileY < 0 || tileY >= WIDTH) {
                    break;
                }
                Entity entity = gameMap.getEntity()[tileX][tileY];

                if (entity instanceof Wall) {
                    break;
                }

                if (i == owner.getLengthFlame()) {
                    switch (direction) {
                        case 0 -> gameMap.getFlames().add(new Flame(tileX, tileY, Sprite.RIGHT_LAST[0], RIGHT_LAST));
                        case 1 -> gameMap.getFlames().add(new Flame(tileX, tileY, Sprite.LEFT_LAST[0], LEFT_LAST));
                        case 2 -> gameMap.getFlames().add(new Flame(tileX, tileY, Sprite.DOWN_LAST[0], DOWN_LAST));
                        case 3 -> gameMap.getFlames().add(new Flame(tileX, tileY, Sprite.TOP_LAST[0], TOP_LAST));
                    }
                } else {
                    switch (direction) {
                        case 0,1 -> gameMap.getFlames().add(new Flame(tileX,tileY,Sprite.HORIZONTAL[0],HORIZONTAL));
                        case 2,3 -> gameMap.getFlames().add(new Flame(tileX,tileY,Sprite.VERTICAL[0],VERTICAL));
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
        timeBeforeExplode--;

        if (timeBeforeExplode == 0) {
            destroy();
            SoundController.playEffectSound(SOUND_PATH[3]);
            buildExplosion();
        }

        if (timeBeforeExplode == -10) {
            delete();
        }
    }

    @Override
    public void delete() {
        gameMap.getBombs().remove(this);
        owner.explosionBomb();
    }
}
