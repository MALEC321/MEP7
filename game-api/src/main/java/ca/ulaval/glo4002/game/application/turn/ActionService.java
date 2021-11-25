package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.turn.Turn;

public class ActionService {
    private final ResourcesFactory resourcesFactory;
    private final ActionFactory actionFactory;

    public ActionService(ResourcesFactory resourcesFactory, ActionFactory actionFactory) {
        this.resourcesFactory = resourcesFactory;
        this.actionFactory = actionFactory;
    }

    public void addCookItConsequence(Turn currentTurn, Pantry pantry) {
        Action addCookIt = actionFactory.createCookItAction(pantry, resourcesFactory);
        currentTurn.addAction(addCookIt);
    }

    public void addRemoveExpiredResourcesConsequence(Turn currentTurn, Pantry pantry) {
        Action removeAllExpiredResourcesAction = actionFactory.createRemoveAllExpiredResourcesAction(pantry);
        currentTurn.addAction(removeAllExpiredResourcesAction);
    }

    public void addFeedDinosaursConsequence(Turn currentTurn, Herd herd, Pantry pantry) {
        Action feedDinosaursAction = actionFactory.createFeedDinosaursAction(pantry, herd);
        currentTurn.addAction(feedDinosaursAction);
    }

    public void addRemoveOrphanedBabyDinosaursConsequence(Turn currentTurn, Herd herd) {
        Action removeOrphanedBabyDinosaurs = actionFactory.createRemoveOrphanedBabyDinosaursAction(herd);
        currentTurn.addAction(removeOrphanedBabyDinosaurs);
    }
}
