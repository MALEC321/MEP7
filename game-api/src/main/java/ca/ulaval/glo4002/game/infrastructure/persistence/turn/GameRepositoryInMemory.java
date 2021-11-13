package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

public class GameRepositoryInMemory implements GameRepository {
    private Game game;

    public GameRepositoryInMemory() {
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
