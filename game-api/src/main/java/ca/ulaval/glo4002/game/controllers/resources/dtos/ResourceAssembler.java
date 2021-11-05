package ca.ulaval.glo4002.game.controllers.resources.dtos;

import ca.ulaval.glo4002.game.domain.resources.ResourceGroup;

import java.util.List;
import java.util.stream.Collectors;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourceAssembler {

    public ResourceDto toDto(ResourceGroup resource) {
        ResourceDto dto = new ResourceDto();
        dto.qtyBurger = resource.getResourceQuantity(BURGER);
        dto.qtySalad = resource.getResourceQuantity(SALAD);
        dto.qtyWater = resource.getResourceQuantity(WATER);

        return dto;
    }

    public List<ResourceDto> toDtos(List<ResourceGroup> resources) {
        return resources.stream().map(this::toDto).collect(Collectors.toList());
    }

}
