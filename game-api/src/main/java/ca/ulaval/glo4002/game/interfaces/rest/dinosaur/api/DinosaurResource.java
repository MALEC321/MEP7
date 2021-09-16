package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.assemblers.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.dtos.DinosaurResponse;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.DinosaurUseCase;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.dtos.DinosaurDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class DinosaurResource {

    private final DinosaurUseCase dinosaurUseCase;
    private final DinosaurDtoAssembler dinosaurDtoAssembler;

    public DinosaurResource(DinosaurUseCase dinosaurUseCase, DinosaurDtoAssembler dinosaurDtoAssembler) {
        this.dinosaurUseCase = dinosaurUseCase;
        this.dinosaurDtoAssembler = dinosaurDtoAssembler;
    }

    @Path("/dinosaurs")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDinosaur(DinosaurDto newDinosaur) {

        DinosaurDto dinosaurDto = new DinosaurDto();
        dinosaurDto.name = newDinosaur.name;
        dinosaurDto.weight = newDinosaur.weight;
        dinosaurDto.gender = newDinosaur.gender;
        dinosaurDto.species = newDinosaur.species;

        dinosaurUseCase.createDinosaur(dinosaurDto.name, dinosaurDto.weight, dinosaurDto.gender, dinosaurDto.species);
        DinosaurResponse response = dinosaurDtoAssembler.toResponse(dinosaurDto);

        return Response.status(Response.Status.CREATED).entity(response).build();
    }
}
