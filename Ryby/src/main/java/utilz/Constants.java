package utilz;

import entities.Player;

public class Constants {
    public static int END=0;
    public static Player PL;
    public static class Directions{
        public static final int UP = 0;
        public static final int DOWN = 1;
        public static final int LEFT = 2;
        public static final int RIGHT = 3;
    }
    public static class PlayerConst{
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int HURT = 2;
        public static final int ATTACK = 3;
        public static final int DEATH = 4;
    }
}
