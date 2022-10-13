package Trace;

import Entities.Animate.Bomb;
import Entities.Animate.Character.Bomber;
import Entities.Animate.Character.Enemy.Enemy;
import Map.Map;

public class BfsVsDodgeTrace extends BfsTrace{
    public BfsVsDodgeTrace(Bomber bomber, Enemy enemy, Map gameMap) {
        super(bomber,enemy, gameMap);
    }

    @Override
    public boolean checkFaceBoom(int X, int Y, int lengthBomb) {
        for (Bomb bomb : gameMap.getBombs()) {
            if (bomb.getTileX() == X && Math.abs(bomb.getTileY() - Y) <= lengthBomb) {
                return true;
            }
            if (bomb.getTileY() == Y && Math.abs(bomb.getTileX() - X) <= lengthBomb) {
                return true;
            }
        }
        return false;
    }
}

