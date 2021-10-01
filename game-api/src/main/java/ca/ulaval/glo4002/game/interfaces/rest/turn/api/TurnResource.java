package ca.ulaval.glo4002.game.interfaces.rest.turn.api;

import ca.ulaval.glo4002.game.interfaces.rest.turn.api.assemblers.TurnDtoAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.turn.api.dtos.TurnResponse;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.TurnUseCase;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.Turn;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTurn() {
        turnUseCase.createTurn();

        TurnDto turnDto = new TurnDto();
        turnDto.turnNumber = Turn.number;
        TurnResponse response = turnDtoAssembler.toResponse(turnDto);

        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @Path("/reset")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reset() {
        Turn.number = 0;

        return Response.status(Response.Status.OK).build();
    }
}