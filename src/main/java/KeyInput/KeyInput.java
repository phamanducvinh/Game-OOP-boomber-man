package KeyInput;


import Constants.Contants;
import Entities.Animate.Character.Bomber;

import java.util.HashMap;

public interface KeyInput {
    void initialization();

    Contants.PLAYER inputToDirection(String code);
    //Contants.DIRECTION handleKeyInput(Bomber bomber);

    HashMap<String, Enum> keyInput = new HashMap<>();
}
