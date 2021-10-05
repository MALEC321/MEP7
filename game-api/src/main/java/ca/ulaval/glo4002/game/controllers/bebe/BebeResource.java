package ca.ulaval.glo4002.game.controllers.bebe;

import ca.ulaval.glo4002.game.application.bebe.BebeUseCase;
import ca.ulaval.glo4002.game.controllers.bebe.dtos.BebeCreationDto;
import ca.ulaval.glo4002.game.controllers.bebe.dtos.BebeDtoAssembler;
import ca.ulaval.glo4002.game.controllers.bebe.dtos.BebeRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/breed")
public class BebeResource {

    private final BebeUseCase bebeUseCase;
    private final BebeDtoAssembler bebeDtoAssembler;

    public BebeResource(BebeUseCase bebeUseCase, BebeDtoAssembler bebeDtoAssembler) {
        this.bebeUseCase = bebeUseCase;
        this.bebeDtoAssembler = bebeDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBebe(BebeRequest bebeRequest) {
        BebeCreationDto dto = bebeDtoAssembler.fromRequest(bebeRequest);
        bebeUseCase.createBebe(dto);

        return Response.status(Response.Status.OK).build();
    }
}
