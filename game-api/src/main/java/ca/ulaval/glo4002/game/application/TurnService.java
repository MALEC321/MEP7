package ca.ulaval.glo4002.game.application;

import ca.ulaval.glo4002.game.domain.Turn;
import ca.ulaval.glo4002.game.domain.TurnRepository;

public class TurnService implements TurnRepository {
    private static int number = 1;
    private final Turn turn;

    public TurnService(Turn turn) {
        this.turn = turn;
    }

    @Override
    public int turn() {
        return number++;
    }

    @Override
    public void reset() {
       number = 1;
    }
}
