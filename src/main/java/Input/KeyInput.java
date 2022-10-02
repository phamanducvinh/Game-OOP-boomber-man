package Input;

import java.util.HashMap;

import static Constants.Contants.DIRECTION;
import Entities.Animate.Character.Bomber;

public abstract class KeyInput {
    HashMap<String, Boolean> keyInput = new HashMap<>();

    public abstract void initialization();

    public void pressedKey(String code) {
        if (keyInput.containsKey(code)) {
            keyInput.put(code, true);
        }
    }

    public void releasedKey(String code) {
        if (keyInput.containsKey(code)) {
            keyInput.put(code, false);
        }
    }

    public abstract DIRECTION handleKeyInput();
}
