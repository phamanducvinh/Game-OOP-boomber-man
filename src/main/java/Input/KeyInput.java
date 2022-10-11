package Input;

import java.util.HashMap;

import static Constants.Constants.DIRECTION;
import Entities.Animate.Character.Bomber;

public interface KeyInput {
    HashMap<String, Boolean> keyInput = new HashMap<>();

    public abstract void initialization();

    public abstract DIRECTION handleKeyInput();
}
