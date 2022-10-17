package Entities.Animate.Character.Enemy;

import Constants.Constants;
import Entities.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Trace.*;
import javafx.scene.canvas.GraphicsContext;

import static Constants.Constants.DIRECTION.*;

public class Ghost extends Enemy{
    private boolean invisible;
    public Ghost(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT,Sprite.GHOST_LEFT);
        animation.put(RIGHT,Sprite.GHOST_RIGHT);
        animation.put(UP,Sprite.GHOST_LEFT);
        animation.put(DOWN,Sprite.GHOST_RIGHT);
        animation.put(DESTROYED,Sprite.GHOST_DEAD);
        currentAnimate = animation.get(RIGHT);
        wallPass = true;
        invisible = true;
    }

    @Override
    public Constants.DIRECTION trace(Bomber bomber, Enemy enemy, Map gameMap) {
        return new BfsVsDodgeTrace(bomber,enemy,gameMap).trace();
    }

    @Override
    public void update() {
        super.update();
        java.util.Random rand = new java.util.Random();
        int random = rand.nextInt(1000);
        invisible = random <= 800;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!invisible) {
            super.render(gc);
        }
    }
}
