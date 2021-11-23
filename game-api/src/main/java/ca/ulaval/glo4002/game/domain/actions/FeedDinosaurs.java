package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.ResourcesStateDto;
import ca.ulaval.glo4002.game.domain.resources.FoodContainer;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;

public class FeedDinosaurs implements Action {

    private HerdRepository herdRepository;
    private Pantry pantry;
    private Herd herd;

    public FeedDinosaurs(HerdRepository herdRepository, Pantry pantry, Herd herd) {
        this.herdRepository = herdRepository;
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
        herdRepository.findHerd().removeAllHungryDinosaur();
        herdRepository.save(herd);
        pantry.removeQuantity(updateResourcesStateDto);
    }
}
