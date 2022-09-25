package Input;

import java.util.HashMap;

import static Constants.Contants.DIRECTION;
import Entities.Animate.Character.Bomber;

public interface KeyInput {
    DIRECTION handleKeyInput(String code);
}
