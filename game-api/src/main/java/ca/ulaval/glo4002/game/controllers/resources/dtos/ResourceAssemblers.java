package ca.ulaval.glo4002.game.controllers.resources.dtos;

import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.game.domain.resources.Resources;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourceAssemblers {

    public ResourceDto toDto(Resources resource) {
        ResourceDto dto = new ResourceDto();
        dto.qtyBurger = resource.getResourceQuantity(BURGER);
        dto.qtySalad = resource.getResourceQuantity(SALAD);
        dto.qtyWater = resource.getResourceQuantity(WATER);

        return dto;
    }

    public List<ResourceDto> toDtos(List<Resources> resources) {
        return resources.stream().map(this::toDto).collect(Collectors.toList());
    }

}
