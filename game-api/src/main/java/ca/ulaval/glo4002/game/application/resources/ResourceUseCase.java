package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.resources.Resource;
import ca.ulaval.glo4002.game.domain.resources.ResourceFactory;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesFactory;

import java.util.ArrayList;
import java.util.List;

public class ResourceUseCase {
    private final ResourcesFactory resourcesFactory;
    private final ResourceRepository resourceRepository;
    private final ResourceAssembler resourceAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;

    public ResourceUseCase(ResourcesFactory resourcesFactory, ResourceRepository resourceRepository, ResourceAssembler resourceAssembler,
                           ActionRepository actionRepository, ActionFactory actionFactory) {
        this.resourcesFactory = resourcesFactory;
        this.resourceRepository = resourceRepository;
        this.resourceAssembler = resourceAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
    }

    public ResourceDto createResource(ResourceCreationDto resourceCreationDto) {
        Resources resources = resourcesFactory.create(resourceCreationDto.qtyBurger, resourceCreationDto.qtySalad, resourceCreationDto.qtyWater);
        addResourceToActionWaitingList(resources);
        return resourceAssembler.toDto(resources);
    }

    public List<ResourceDto> getAllResources() {
        List<Resources> resources = resourceRepository.findAll();
        return resourceAssembler.toDtos(resources);
    }

    private void addResourceToActionWaitingList(Resources resources) {
        for (Resource resource : new ArrayList<>(resources.getResources().values())) {
            if (resource.isEmpty()) {
                continue;
            }

            Action addResources = actionFactory.create(resource, resourceRepository);
            actionRepository.save(addResources);
        }
    }
}
