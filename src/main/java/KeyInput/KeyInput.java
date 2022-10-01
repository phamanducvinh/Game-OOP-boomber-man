package KeyInput;


import Constants.Contants;
import Entities.Animate.Character.Bomber;

import java.util.HashMap;

public interface KeyInput {
    HashMap<Contants.DIRECTION,Boolean> keyInput = new HashMap<>();
    void initialization();
    Contants.DIRECTION handleKeyInput(Bomber bomber);
}
