package Input;

import Constants.Contants;
import Entities.Animate.Character.Bomber;
import Input.KeyInput;

import java.util.Set;

public class PlayerTwoKeyInput extends KeyInput {

    @Override
    public void initialization() {
        keyInput.put("LEFT", false);
        keyInput.put("RIGHT", false);
        keyInput.put("UP", false);
        keyInput.put("DOWN", false);
        keyInput.put("ENTER", false);
    }

    @Override
    public Contants.DIRECTION handleKeyInput() {
        Set<String> keySet = keyInput.keySet();
        for (String code : keySet) {
            if (keyInput.get(code)) {
                System.out.println(code);
                switch (code) {
                    case ("SPACE"):
                        releasedKey(code);
                        return Contants.DIRECTION.DESTROYED;
                    case ("W"):
                        return Contants.DIRECTION.UP;
                    case ("D"):
                        return Contants.DIRECTION.RIGHT;
                    case ("S"):
                        return Contants.DIRECTION.DOWN;
                    case ("A"):
                        return Contants.DIRECTION.LEFT;
                }
            }
        }
        return Contants.DIRECTION.NONE;
    }
}
