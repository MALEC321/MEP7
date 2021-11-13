package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

import ca.ulaval.glo4002.game.domain.turn.Game;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;

public class TurnRepositoryInMemory implements TurnRepository {
    private Game game;

    public TurnRepositoryInMemory() {
        this.game = new Game();
    }

    @Override
    public void save(Game game) {
        this.game = game;
    }

    @Override
    public Game findGame() {
        return this.game;
    }

    @Override
    public void deleteAll() {
        game = new Game();
    }
}
