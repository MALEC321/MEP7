package ca.ulaval.glo4002.game.interfaces.rest.resources.api.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.resources.api.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.interfaces.rest.resources.api.dtos.ResourceResponse;
import ca.ulaval.glo4002.game.interfaces.rest.resources.application.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.interfaces.rest.resources.application.dtos.ResourceDto;

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
}
