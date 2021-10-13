package ca.ulaval.glo4002.game.controllers.resources.dtos;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceDtoAssembler {
    public ResourceCreationDto fromRequest(ResourceRequest request) {
        ResourceCreationDto dto = new ResourceCreationDto();

        dto.qtyBurger = request.qtyBurger;
        dto.qtySalad = request.qtySalad;
        dto.qtyWater = request.qtyWater;

        return dto;
    }

    public ResourceResponse toResponse(ResourceDto resourceDto) {
        ResourceResponse response = new ResourceResponse();
        response.qtyBurger = resourceDto.qtyBurger;
        response.qtySalad = resourceDto.qtySalad;
        response.qtyWater = resourceDto.qtyWater;

        return response;
    }

    public ResourcesResponse toResponse(List<ResourceDto> resourceDtos) {
        ResourcesResponse response = new ResourcesResponse();
        response.fresh = resourceDtos.stream().map(this::toResponse).collect(Collectors.toList()).get(0);
        response.expired = resourceDtos.stream().map(this::toResponse).collect(Collectors.toList()).get(1);
        response.consumed = resourceDtos.stream().map(this::toResponse).collect(Collectors.toList()).get(2);

        return response;
    }
}
