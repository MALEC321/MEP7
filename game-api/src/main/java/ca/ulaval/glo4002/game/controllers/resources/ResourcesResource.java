package ca.ulaval.glo4002.game.controllers.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesUseCase;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourcesResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/resources")
public class ResourcesResource {
    private final ResourcesUseCase resourcesUseCase;
    private final ResourceDtoAssembler resourceDtoAssembler;

    public ResourcesResource(ResourcesUseCase resourcesUseCase, ResourceDtoAssembler resourceDtoAssembler) {
        this.resourcesUseCase = resourcesUseCase;
        this.resourceDtoAssembler = resourceDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createResources(ResourceRequest resourceRequest) {
        ResourceCreationDto dto = resourceDtoAssembler.fromRequest(resourceRequest);
        resourcesUseCase.createResources(dto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResources() {
        List<ResourcesDto> resources = resourcesUseCase.getAllResources();
        ResourcesResponse response = resourceDtoAssembler.toResponse(resources);
        return Response.status(Response.Status.OK).entity(response).build();
    }
}
