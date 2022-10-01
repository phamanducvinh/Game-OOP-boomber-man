package KeyInput;

import Constants.Contants;
import Entities.Animate.Character.Bomber;

import static Constants.Contants.DIRECTION.*;

public class Player implements KeyInput {
    @Override
    public void initialization() {
        keyInput.put(LEFT,false);
        keyInput.put(RIGHT,false);
        keyInput.put(UP,false);
        keyInput.put(DOWN,false);
        keyInput.put(DESTROYED,false);
    }

    @Override
    public Contants.DIRECTION handleKeyInput(Bomber bomber) {
        if (keyInput.get(DESTROYED)) {
            keyInput.put(DESTROYED, false);
            bomber.placeBomb();
        }
        if (keyInput.get(LEFT)) {
            return LEFT;
        }
        if (keyInput.get(RIGHT)) {
            return RIGHT;
        }
        if (keyInput.get(UP)) {
            return UP;
        }
        if (keyInput.get(DOWN)) {
            return DOWN;
        }
        return DESTROYED;
    }
}
