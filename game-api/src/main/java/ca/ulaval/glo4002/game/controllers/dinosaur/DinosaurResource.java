package ca.ulaval.glo4002.game.controllers.dinosaur;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurUseCase;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurResponseItem;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaursResponse;

@Path("/dinosaurs")
public class DinosaurResource {

    private final DinosaurUseCase dinosaurUseCase;
    private final DinosaurDtoAssembler dinosaurDtoAssembler;

    public DinosaurResource(DinosaurUseCase dinosaurUseCase, DinosaurDtoAssembler dinosaurDtoAssembler) {
        this.dinosaurUseCase = dinosaurUseCase;
        this.dinosaurDtoAssembler = dinosaurDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDinosaur(DinosaurRequest dinosaurRequest) {
        DinosaurCreationDto dto = dinosaurDtoAssembler.fromRequest(dinosaurRequest);
        dinosaurUseCase.createDinosaur(dto);

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDinosaurs() {
        List<DinosaurDto> dinosaurs = dinosaurUseCase.getAllDinosaurs();
        DinosaursResponse response = dinosaurDtoAssembler.toResponse(dinosaurs);
        return Response.ok().entity(response.items).build();
    }

    @Path("{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDinosaur(@PathParam("name") String name) {
        DinosaurDto dinosaur = dinosaurUseCase.getDinosaur(name);
        DinosaurResponseItem response = dinosaurDtoAssembler.toResponse(dinosaur);
        return Response.ok().entity(response).build();
    }
}
