package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;

public class TurnEffect {
    private final TurnFactory turnFactory;
    private final ActionService actionService;

    public TurnEffect(TurnFactory turnFactory, ActionService actionService) {
        this.turnFactory = turnFactory;
        this.actionService = actionService;
    }

    public void addTurnConsequences(Turn currentTurn, Herd herd, Pantry pantry) {
        actionService.addCookItConsequence(currentTurn, pantry);
        actionService.addRemoveExpiredResourcesConsequence(currentTurn, pantry);
        actionService.addFeedDinosaursConsequence(currentTurn, herd, pantry);
        actionService.addRemoveOrphanedBabyDinosaursConsequence(currentTurn, herd);
    }

    public void endTurn(Game game) {
        Turn turn = turnFactory.create(game.nextTurnNumber());
        game.addTurn(turn);
    }
}
