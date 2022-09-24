package Input;

import java.util.HashMap;

import static Constants.Contants.DIRECTION;
import Entities.Animate.Character.Bomber;

public interface KeyInput {
    HashMap<String, DIRECTION> keyInput = new HashMap<>();
    void initialization();
    DIRECTION handleKeyInput(Bomber bomber);
}
