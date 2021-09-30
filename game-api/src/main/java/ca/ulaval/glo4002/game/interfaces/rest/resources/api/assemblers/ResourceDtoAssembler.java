package ca.ulaval.glo4002.game.interfaces.rest.resources.api.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.resources.api.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.interfaces.rest.resources.api.dtos.ResourceResponse;
import ca.ulaval.glo4002.game.interfaces.rest.resources.api.dtos.ResourcesResponse;
import ca.ulaval.glo4002.game.interfaces.rest.resources.application.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.interfaces.rest.resources.application.dtos.ResourceDto;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceDtoAssembler {
    public ResourceCreationDto fromRequest(ResourceRequest request) {
        ResourceCreationDto dto = new ResourceCreationDto();

//        if (request.qtyBurger < 0 || request.qtySalad < 0 || request.qtyWater < 0) {
//            throw new InvalidResourceQuantityException();
//        }

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
