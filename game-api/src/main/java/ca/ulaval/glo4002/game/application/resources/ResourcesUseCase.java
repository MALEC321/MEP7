package ca.ulaval.glo4002.game.application.resources;

import java.util.List;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourcesAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;

public class ResourcesUseCase {
    private final ResourcesGroupFactory resourcesGroupFactory;
    private final PantryRepository pantryRepository;
    private final ResourcesAssembler resourcesAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;

    public ResourcesUseCase(ResourcesGroupFactory resourcesGroupFactory, PantryRepository pantryRepository, ResourcesAssembler resourcesAssembler,
                            ActionRepository actionRepository, ActionFactory actionFactory) {
        this.resourcesGroupFactory = resourcesGroupFactory;
        this.pantryRepository = pantryRepository;
        this.resourcesAssembler = resourcesAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
    }

    public void createResources(ResourceCreationDto resourceCreationDto) {
        ResourcesGroup addResources =
            resourcesGroupFactory.create(resourceCreationDto.getQtyBurger(), resourceCreationDto.getQtySalad(), resourceCreationDto.getQtyWater());
        addResourcesToActionWaitingList(addResources);
        resourcesAssembler.toDto(addResources);
    }

    public List<ResourcesDto> getAllResources() {
        Pantry pantry = pantryRepository.findPantry();
        List<ResourcesGroup> resources = pantry.findAll();
        return resourcesAssembler.toDtos(resources);
    }

    private void addResourcesToActionWaitingList(ResourcesGroup resourcesGroup) {
        for (Resources resources : resourcesGroup.getResources().values()) {
            if (!resources.isEmpty()) {
                Action addResources = actionFactory.create(resources, pantryRepository);
                actionRepository.save(addResources);
            }
        }
    }
}
