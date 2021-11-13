package ca.ulaval.glo4002.game.controllers.turn;

import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDto;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDtoAssembler;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TurnResource {
    private final TurnUseCase turnUseCase;
    private final TurnDtoAssembler turnDtoAssembler;

    public TurnResource(TurnUseCase turnUseCase,
                        TurnDtoAssembler turnDtoAssembler) {
        this.turnUseCase = turnUseCase;
        this.turnDtoAssembler = turnDtoAssembler;
    }

    @Path("/turn")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response runTurn() {
        turnUseCase.executeTurn();

        TurnDto turnDto = new TurnDto(turnUseCase.getTurnNumber());
        TurnResponse response = turnDtoAssembler.toResponse(turnDto);

        return Response.status(Response.Status.OK).entity(response).build();
    }

    @Path("/reset")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reset() {
        turnUseCase.reset();

        return Response.status(Response.Status.OK).build();
    }
}
