package ca.ulaval.glo4002.game.interfaces.rest.resources.application.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.resources.application.dtos.ResourceDto;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.Resource;

import java.util.List;
import java.util.stream.Collectors;

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
        dto.qtyBurger = resource.getBurgersQuantity();
        dto.qtySalad = resource.getSaladQuantity();
        dto.qtyWater = resource.getWaterQuantity();

        return dto;
    }

    public List<ResourceDto> toDtos(List<Resource> resources) {
        return resources.stream().map(this::toDto).collect(Collectors.toList());
    }

    }
