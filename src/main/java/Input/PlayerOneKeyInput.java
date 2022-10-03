package Input;

import Constants.Contants;

import java.util.Set;

public class PlayerOneKeyInput extends KeyInput {
    public PlayerOneKeyInput() {
    }

    public void initialization() {
        keyInput.put("A", false);
        keyInput.put("D", false);
        keyInput.put("W", false);
        keyInput.put("S", false);
        keyInput.put("SPACE", false);
    }

    @Override
    public Contants.DIRECTION handleKeyInput() {
        Set<String> keySet = keyInput.keySet();
        for (String code : keySet) {
            if (keyInput.get(code)) {
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
