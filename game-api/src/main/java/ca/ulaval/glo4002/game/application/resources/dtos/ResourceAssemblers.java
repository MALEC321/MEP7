package ca.ulaval.glo4002.game.application.resources.dtos;

import ca.ulaval.glo4002.game.domain.resources.Resource;

import java.util.List;
import java.util.stream.Collectors;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourceAssemblers {

    public ResourceDto toDto(Resource resource) {
        return new ResourceDto(resource.getResourceQuantity(BURGER), resource.getResourceQuantity(SALAD),
                resource.getResourceQuantity(WATER));
    }

    public List<ResourceDto> toDtos(List<Resource> resources) {
        return resources.stream().map(this::toDto).collect(Collectors.toList());
    }

}
