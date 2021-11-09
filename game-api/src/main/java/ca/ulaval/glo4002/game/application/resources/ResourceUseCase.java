package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.resources.Resource;
import ca.ulaval.glo4002.game.domain.resources.ResourceGroup;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

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

    public ResourceDto createResource(ResourceCreationDto resourceCreationDto) {
        ResourceGroup resourceGroup = resourceGroupFactory.create(resourceCreationDto.qtyBurger, resourceCreationDto.qtySalad, resourceCreationDto.qtyWater);
        addResourceToActionWaitingList(resourceGroup);
        return resourceAssembler.toDto(resourceGroup);
    }

    public List<ResourceDto> getAllResources() {
        List<ResourceGroup> resources = resourceRepository.findAll();
        return resourceAssembler.toDtos(resources);
    }

    private void addResourceToActionWaitingList(ResourceGroup resourceGroup) {
        for (Resource resource : resourceGroup.getResources().values()) {
            if (resource.isEmpty()) {
                continue;
            }

            Action addResources = actionFactory.create(resource, resourceRepository);
            actionRepository.save(addResources);
        }
    }
}
