package ca.ulaval.glo4002.game.controllers.resources.dtos;

import ca.ulaval.glo4002.game.domain.resources.Resource;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceAssemblers {
    public ResourceDto toDto(Resource resource) {
        ResourceDto dto = new ResourceDto();
        dto.qtyBurger = resource.getBurgersQuantity();
        dto.qtySalad = resource.getSaladQuantity();
        dto.qtyWater = resource.getWaterQuantity();

        return dto;
    }

    public List<ResourceDto> toDtos(List<Resource> resources) {
        return resources.stream().map(this::toDto).collect(Collectors.toList());
    }

}
