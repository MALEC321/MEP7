package ca.ulaval.glo4002.game.domain.actions;

import java.util.List;

import ca.ulaval.glo4002.game.application.exceptions.ArmsTooShortException;
import ca.ulaval.glo4002.game.application.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.application.exceptions.MaxCombatsReachedException;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;

public class ActionFactory {
    public AddDino createAddDinoAction(Dinosaur dinosaur, Herd herd) {
        return new AddDino(dinosaur, herd);
    }

    public AddResource createAddResourceAction(Resources resources, Pantry pantry) {
        return new AddResource(resources, pantry);
    }

    public Action createFight(List<Action> actions, Dinosaur challenger, Dinosaur challengee, Herd herd) {
        validateNumberOfCurrentFights(actions);
        validateFighters(challenger, challengee);
        challenger.fight();
        challengee.fight();
        return new FightAction(challenger, challengee, herd);
    }


    public void validateNumberOfCurrentFights(List<Action> actions) {
        int numberOfCurrentFights = 0;
        for (Action action : actions) {
            if (action instanceof FightAction) {
                numberOfCurrentFights++;
            }
        }
        if (numberOfCurrentFights >= 2) {
            throw new MaxCombatsReachedException();
        }
    }

    public void validateFighters(Dinosaur challenger, Dinosaur challengee) {
        if (challenger == null || challengee == null) {
            throw new NotExistentNameException();
        } else if (challenger.getSpecies().equals("Tyrannosaurus Rex") || challengee.getSpecies().equals("Tyrannosaurus Rex")) { //Un enum aurait-il été mieux?
            throw new ArmsTooShortException();
        } else if (challenger.isFighting() || challengee.isFighting()) {
            throw new DinosaurAlreadyParticipatingException();
        }
    }

    public CookIt createCookItAction(Pantry pantry, ResourcesFactory resourcesFactory) {
        return new CookIt(pantry, resourcesFactory);
    }

    public FeedDinosaurs createFeedDinosaursAction(ResourcesDistributor resourcesDistributor, Pantry pantry, Herd herd) {
        return new FeedDinosaurs(resourcesDistributor, pantry, herd);
    }

    public RemoveExpiredResources createRemoveAllExpiredResourcesAction(Pantry pantry) {
        return new RemoveExpiredResources(pantry);
    }

    public RemoveOrphanedBabyDinosaurs createRemoveOrphanedBabyDinosaursAction(Herd herd) {
        return new RemoveOrphanedBabyDinosaurs(herd);
    }
}
