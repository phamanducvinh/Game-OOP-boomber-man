package Input;

import java.util.HashMap;

import Constants.Contants;
import Entities.Animate.Character.Bomber;

public interface KeyInput {
    HashMap<String, Boolean> keyInput = new HashMap<>();
    void initialization();
    Contants.DIRECTION handleKeyInput(Bomber bomber);
}
