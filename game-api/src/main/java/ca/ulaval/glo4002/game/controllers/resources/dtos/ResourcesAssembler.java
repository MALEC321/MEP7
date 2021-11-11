package ca.ulaval.glo4002.game.controllers.resources.dtos;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;

import java.util.List;
import java.util.stream.Collectors;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourcesAssembler {
    public ResourcesDto toDto(ResourcesGroup resourcesGroup) {
        return new ResourcesDto(resourcesGroup.getResourceQuantity(BURGER), resourcesGroup.getResourceQuantity(SALAD),
                resourcesGroup.getResourceQuantity(WATER));
    }

    public List<ResourcesDto> toDtos(List<ResourcesGroup> resources) {
        return resources.stream().map(this::toDto).collect(Collectors.toList());
    }

}
