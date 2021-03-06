package ca.ulaval.glo4002.game.controllers.baby;

import ca.ulaval.glo4002.game.application.baby.BabyService;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyDtoAssembler;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/breed")
public class BabyResource {
    private final BabyService babyService;
    private final BabyDtoAssembler babyDtoAssembler;

    public BabyResource(BabyService babyService, BabyDtoAssembler babyDtoAssembler) {
        this.babyService = babyService;
        this.babyDtoAssembler = babyDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBaby(BabyRequest babyRequest) {
        BabyCreationDto dto = babyDtoAssembler.fromRequest(babyRequest);
        babyService.createBaby(dto);
        return Response.status(Response.Status.OK).build();
    }
}
