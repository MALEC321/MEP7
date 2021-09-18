package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceElements;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;

import java.util.UUID;

public class AddAction extends Actions{
    public AddAction(UUID id, ResourceElements resource, ResourceRepository resourceRepository) {
        super(id, resource, resourceRepository);
    }

    @Override
    public void execute() {
        getResourceRepository().add(getResource());
        System.out.println(getResourceRepository().findAll().size());
    }
}
