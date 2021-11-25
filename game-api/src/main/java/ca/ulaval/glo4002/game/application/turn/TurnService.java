package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;

public class TurnService {
    private final GameRepository gameRepository;
    private final PantryRepository pantryRepository;
    private final HerdRepository herdRepository;
    private final TurnEffect turnEffect;

    public TurnService(GameRepository gameRepository, PantryRepository pantryRepository, HerdRepository herdRepository,
                       TurnEffect turnEffect) {
        this.gameRepository = gameRepository;
        this.pantryRepository = pantryRepository;
        this.herdRepository = herdRepository;
        this.turnEffect = turnEffect;
    }

    public void playTurn() {
        Game game = gameRepository.findGame();
        Herd herd = herdRepository.findHerd();
        Pantry pantry = pantryRepository.findPantry();

        Turn currentTurn = game.currentTurn();

        turnEffect.addTurnConsequences(currentTurn, herd, pantry);
        currentTurn.executeActions();

        turnEffect.endTurn(game);

        pantryRepository.save(pantry);
        herdRepository.save(herd);
        gameRepository.save(game);
    }

    public TurnNumber getLastPlayedTurnNumber() {
        Game game = gameRepository.findGame();
        return game.lastPlayedTurnNumber();
    }

    public void resetGame() {
        gameRepository.deleteAll();
        pantryRepository.deleteAll();
        herdRepository.deleteAll();
    }
}
