package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.actions.Command;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceAssemblers;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDto;
import ca.ulaval.glo4002.game.domain.resources.Resource;
import ca.ulaval.glo4002.game.domain.resources.ResourceElements;
import ca.ulaval.glo4002.game.domain.resources.ResourceFactory;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

import java.util.ArrayList;
import java.util.List;

public class ResourceUseCase {
    private final ResourceFactory resourceFactory;
    private final ResourceRepository resourceRepository;
    private final ResourceAssemblers resourceAssemblers;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;

    public ResourceUseCase(ResourceFactory resourceFactory, ResourceRepository resourceRepository, ResourceAssemblers resourceAssemblers, ActionRepository actionRepository, ActionFactory actionFactory) {
        this.resourceFactory = resourceFactory;
        this.resourceRepository = resourceRepository;
        this.resourceAssemblers = resourceAssemblers;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
    }

    public ResourceDto createResource(ResourceCreationDto resourceCreationDto) {
        Resource resources = resourceFactory.create(resourceCreationDto.qtyBurger, resourceCreationDto.qtySalad, resourceCreationDto.qtyWater);
        addResourceToActionWaitingList(resources);
        return resourceAssemblers.toDto(resources);
    }

    public List<ResourceDto> getAllResources() {
        List<Resource> resources = resourceRepository.findAll();
        return resourceAssemblers.toDtos(resources);
    }

    private void addResourceToActionWaitingList(Resource resources) {
        for (ResourceElements resource: new ArrayList<>(resources.getResources().values())) {
            if (resource.getQuantity() == 0 ) { continue;}
            Action addResources = actionFactory.create(resource, Command.ADD, resourceRepository);
            actionRepository.save(addResources);
        }
    }
}
