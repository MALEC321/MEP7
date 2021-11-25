package ca.ulaval.glo4002.game.controllers.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesService;
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
    private final ResourcesService resourcesService;
    private final ResourceDtoAssembler resourceDtoAssembler;

    public ResourcesResource(ResourcesService resourcesService, ResourceDtoAssembler resourceDtoAssembler) {
        this.resourcesService = resourcesService;
        this.resourceDtoAssembler = resourceDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createResources(ResourceRequest resourceRequest) {
        ResourceCreationDto dto = resourceDtoAssembler.fromRequest(resourceRequest);
        resourcesService.createResources(dto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResources() {
        List<ResourcesDto> resources = resourcesService.getAllResources();
        ResourcesResponse response = resourceDtoAssembler.toResponse(resources);
        return Response.status(Response.Status.OK).entity(response).build();
    }
}
