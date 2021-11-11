package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import ca.ulaval.glo4002.game.domain.turn.Turns;

public class TurnRepositoryInMemory implements TurnRepository {
    private Turns turns;

    public TurnRepositoryInMemory() {
        this.turns = new Turns();
    }

    @Override
    public void save(Turns turns) {
        this.turns = turns;
    }

    @Override
    public Turns findTurns() {
        return this.turns;
    }

    @Override
    public void reset() {
        turns = new Turns();
    }
}
