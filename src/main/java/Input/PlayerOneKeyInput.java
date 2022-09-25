package Input;

import Constants.Contants;

public class PlayerOneKeyInput implements KeyInput {
    public PlayerOneKeyInput() {
    }
    @Override
    public Contants.DIRECTION handleKeyInput(String code) {
        switch (code) {
            case ("SPACE"):
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
        return Contants.DIRECTION.NONE;
    }
}
