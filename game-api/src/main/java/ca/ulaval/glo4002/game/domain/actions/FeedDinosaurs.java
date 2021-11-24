package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.ResourcesStateDto;
import ca.ulaval.glo4002.game.domain.resources.Pantry;

public class FeedDinosaurs implements Action {

    private Pantry pantry;
    private Herd herd;

    public FeedDinosaurs(Pantry pantry, Herd herd) {
        this.pantry = pantry;
        this.herd = herd;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public Herd getHerd() {
        return herd;
    }

    @Override
    public void execute() {
        ResourcesStateDto resourcesStateDto = pantry.getFreshResourcesReport();
        ResourcesStateDto updateResourcesStateDto = herd.feedDinosaurs(resourcesStateDto);
        herd.removeAllHungryDinosaur();
        pantry.removeQuantity(updateResourcesStateDto);
    }
}
