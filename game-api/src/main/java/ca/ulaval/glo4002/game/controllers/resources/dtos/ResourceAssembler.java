package ca.ulaval.glo4002.game.controllers.resources.dtos;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourceDto;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;

import java.util.List;
import java.util.stream.Collectors;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourceAssembler {
    public ResourceDto toDto(ResourcesGroup resourcesGroup) {
        return new ResourceDto(resourcesGroup.getResourceQuantity(BURGER), resourcesGroup.getResourceQuantity(SALAD),
                resourcesGroup.getResourceQuantity(WATER));
    }

    public List<ResourceDto> toDtos(List<ResourcesGroup> resources) {
        return resources.stream().map(this::toDto).collect(Collectors.toList());
    }

}
