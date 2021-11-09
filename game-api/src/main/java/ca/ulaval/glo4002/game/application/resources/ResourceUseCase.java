package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourceDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;

import java.util.List;

public class ResourceUseCase {
    private final ResourceGroupFactory resourceGroupFactory;
    private final ResourceRepository resourceRepository;
    private final ResourceAssembler resourceAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;

    public ResourceUseCase(ResourceGroupFactory resourceGroupFactory, ResourceRepository resourceRepository, ResourceAssembler resourceAssembler,
                           ActionRepository actionRepository, ActionFactory actionFactory) {
        this.resourceGroupFactory = resourceGroupFactory;
        this.resourceRepository = resourceRepository;
        this.resourceAssembler = resourceAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
    }

    public void createResource(ResourceCreationDto resourceCreationDto) {
        ResourcesGroup addResources = resourceGroupFactory.create(resourceCreationDto.getQtyBurger(), resourceCreationDto.getQtySalad(), resourceCreationDto.getQtyWater());
        addResourceToActionWaitingList(addResources);
        resourceAssembler.toDto(addResources);
    }

    public List<ResourceDto> getAllResources() {
        List<ResourcesGroup> resources = resourceRepository.findAll();
        return resourceAssembler.toDtos(resources);
    }

    private void addResourceToActionWaitingList(ResourcesGroup resourcesGroup) {
        for (Resources resources : resourcesGroup.getResources().values()) {
            if (!resources.isEmpty()) {
                Action addResources = actionFactory.create(resources, resourceRepository);
                actionRepository.save(addResources);
            }
        }
    }
}
