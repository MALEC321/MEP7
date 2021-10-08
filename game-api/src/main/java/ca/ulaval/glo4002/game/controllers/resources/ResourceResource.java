package ca.ulaval.glo4002.game.controllers.resources;

import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceRequest;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourcesResponse;
import ca.ulaval.glo4002.game.application.resources.ResourceUseCase;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/resources")
public class ResourceResource {

    private final ResourceUseCase resourceUseCase;
    private final ResourceDtoAssembler resourceDtoAssembler;

    public ResourceResource(ResourceUseCase resourceUseCase, ResourceDtoAssembler resourceDtoAssembler) {
        this.resourceUseCase = resourceUseCase;
        this.resourceDtoAssembler = resourceDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createResource(ResourceRequest resourceRequest) {

        ResourceCreationDto dto = resourceDtoAssembler.fromRequest(resourceRequest);
        resourceUseCase.createResource(dto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResources() {
        List<ResourceDto> resources = resourceUseCase.getAllResources();
        ResourcesResponse response = resourceDtoAssembler.toResponse(resources);

        return Response.status(Response.Status.OK).entity(response).build();
    }

}
