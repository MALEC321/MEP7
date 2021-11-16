package ca.ulaval.glo4002.game.controllers.sumo;

import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoDtoAssembler;
import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoRequest;
import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sumodino")
public class SumoResource {
    private final SumoUseCase sumoUseCase;
    private final SumoDtoAssembler sumoDtoAssembler;

    public SumoResource(SumoUseCase sumoUseCase, SumoDtoAssembler sumoDtoAssembler) {
        this.sumoUseCase = sumoUseCase;
        this.sumoDtoAssembler = sumoDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDinosaur(SumoRequest sumoRequest) {
        SumoDto dto = new SumoDto();
        dto.challengee = sumoRequest.challengee;
        dto.challenger = sumoRequest.challenger;

        SumoResponse response = sumoUseCase.fight(dto);
        return Response.ok().entity(response).build();
    }
}
