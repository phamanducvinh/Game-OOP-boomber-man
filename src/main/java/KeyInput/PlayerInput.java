package KeyInput;

import Constants.Contants;

import static Constants.Contants.PLAYER.*;

public class PlayerInput implements KeyInput {
    @Override
    public void initialization() {
        keyInput.put("A",LEFT);
        keyInput.put("W",UP);
        keyInput.put("S",DOWN);
        keyInput.put("D",RIGHT);
        keyInput.put("SPACE",PLACE_BOMB);
    }

    @Override
    public Contants.PLAYER inputToDirection(String code) {
        System.out.println(code);
        return  (Contants.PLAYER) keyInput.get(code);
    }
}
