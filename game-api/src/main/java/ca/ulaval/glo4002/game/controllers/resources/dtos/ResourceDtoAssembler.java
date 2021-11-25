package ca.ulaval.glo4002.game.controllers.resources.dtos;

import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;

public class ResourceDtoAssembler {
    public ResourceCreationDto fromRequest(ResourceRequest request) {
        return new ResourceCreationDto(request.getQtyBurger(), request.getQtySalad(),
            request.getQtyWater());
    }

    public ResourceResponse toResponse(ResourcesDto resourcesDto) {
        return new ResourceResponse(resourcesDto.getQtyBurger(), resourcesDto.getQtySalad(),
            resourcesDto.getQtyWater());
    }

    public ResourcesResponse toResponse(List<ResourcesDto> resourcesDtos) {
        return new ResourcesResponse(
            resourcesDtos.stream().map(this::toResponse).collect(Collectors.toList()).get(0),
            resourcesDtos.stream().map(this::toResponse).collect(Collectors.toList()).get(1),
            resourcesDtos.stream().map(this::toResponse).collect(Collectors.toList()).get(2)
        );
    }
}
