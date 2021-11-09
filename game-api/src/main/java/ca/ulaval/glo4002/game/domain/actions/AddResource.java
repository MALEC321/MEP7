package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.ResourceElements;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

public class AddResource implements Action {

    private ResourceElements resourceElements;
    private ResourceRepository resourceRepository;

    public AddResource(ResourceElements resourceElements, ResourceRepository resourceRepository) {
        this.resourceElements = resourceElements;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void execute() {
        resourceRepository.add(resourceElements);
    }
}
