package ca.ulaval.glo4002.game.domain;

import java.util.Random;

public class Tour {
    private final int ID = new Random().nextInt(100);

    public int turn(){
        return ID;
    }
}
