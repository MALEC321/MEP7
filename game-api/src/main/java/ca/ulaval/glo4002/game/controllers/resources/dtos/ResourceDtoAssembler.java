package ca.ulaval.glo4002.game.controllers.resources.dtos;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourceDto;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceDtoAssembler {
    public ResourceCreationDto fromRequest(ResourceRequest request) {
        return new ResourceCreationDto(request.getQtyBurger(), request.getQtySalad(),
                request.getQtyWater());
    }

    public ResourceResponse toResponse(ResourceDto resourceDto) {
        return new ResourceResponse(resourceDto.getQtyBurger(), resourceDto.getQtySalad(),
                resourceDto.getQtyWater());
    }

    public ResourcesResponse toResponse(List<ResourceDto> resourceDtos) {
        ResourcesResponse response = new ResourcesResponse(
                resourceDtos.stream().map(this::toResponse).collect(Collectors.toList()).get(0),
                resourceDtos.stream().map(this::toResponse).collect(Collectors.toList()).get(1),
                resourceDtos.stream().map(this::toResponse).collect(Collectors.toList()).get(2)
        );
        return response;
    }
}
