package Entities.Still.Item;

import Entities.Animate.Bomb;
import Entities.Animate.Character.Bomber;
import Graphics.Sprite;

public class Random extends Item {

    public Random(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void effect(Bomber bomber) {
        int rand = new java.util.Random().nextInt(8);
        switch (rand) {
            case 0 -> bomber.setMaxBombs(bomber.getMaxBombs() + 1);
            case 1 -> bomber.setLengthFlame(bomber.getLengthFlame() + 1);
            case 2 -> bomber.setSpeed(bomber.getSpeed() + 1);
            case 3 -> bomber.setWallPass(true);
            case 4 -> bomber.setLife(bomber.getLife() + 1);
            case 5 -> bomber.setBombPass(true);
            case 6 -> bomber.setFlamePass(true);
            case 7 -> {
                if (bomber.getLife() > 1) {
                    bomber.setLife(bomber.getLife() - 1);
                }
            }
        }

        destroy();
    }
}
