package ca.ulaval.glo4002.game.controllers.resources.dtos;

import ca.ulaval.glo4002.game.domain.resources.Resource;

import java.util.List;
import java.util.stream.Collectors;
import static ca.ulaval.glo4002.game.domain.resources.ResourceTypesEnum.*;


public class ResourceAssemblers {

//    public ResourceDto toDto(Resource resource) {
//        ResourceDto dto = new ResourceDto();
//        dto.qtyBurger = resource.getResources().get("Burgers").getQuantity();
//        dto.qtySalad = resource.getResources().get("Salads").getQuantity();
//        dto.qtyWater = resource.getResources().get("Water").getQuantity();
//
//        return dto;
//    }

    public ResourceDto toDto(Resource resource) {
        ResourceDto dto = new ResourceDto();
        dto.qtyBurger = resource.getResourceQuantity(Burger);
        dto.qtySalad = resource.getResourceQuantity(Salad);
        dto.qtyWater = resource.getResourceQuantity(Water);

        return dto;
    }

    public List<ResourceDto> toDtos(List<Resource> resources) {
        return resources.stream().map(this::toDto).collect(Collectors.toList());
    }

    }
