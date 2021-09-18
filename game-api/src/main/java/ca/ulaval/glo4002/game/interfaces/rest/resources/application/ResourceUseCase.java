package ca.ulaval.glo4002.game.interfaces.rest.resources.application;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionFactory;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionRepository;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Actions;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Command;
import ca.ulaval.glo4002.game.interfaces.rest.resources.application.assemblers.ResourceAssemblers;
import ca.ulaval.glo4002.game.interfaces.rest.resources.application.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.interfaces.rest.resources.application.dtos.ResourceDto;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.Resource;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceElements;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceFactory;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;

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
        List<Actions> actions = actionRepository.getWaitingActions();

        Resource resources = resourceFactory.create(resourceCreationDto.qtyBurger, resourceCreationDto.qtySalad, resourceCreationDto.qtyWater);
        addResourceToActionWaitingList(resources);
        return resourceAssemblers.toDto(resources);
    }

    private void addResourceToActionWaitingList(Resource resources) {
        for (ResourceElements resource: new ArrayList<>(resources.getResources().values())) {
            Actions addResources = actionFactory.create(resource, Command.ADD, resourceRepository);
            actionRepository.save(addResources);
        }
    }
}
