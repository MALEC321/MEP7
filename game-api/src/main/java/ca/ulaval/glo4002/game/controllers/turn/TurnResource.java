package ca.ulaval.glo4002.game.controllers.turn;

import ca.ulaval.glo4002.game.application.turn.TurnService;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDto;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDtoAssembler;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TurnResource {
    private final TurnService turnService;
    private final TurnDtoAssembler turnDtoAssembler;

    public TurnResource(TurnService turnService,
                        TurnDtoAssembler turnDtoAssembler) {
        this.turnService = turnService;
        this.turnDtoAssembler = turnDtoAssembler;
    }

    @Path("/turn")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response runTurn() {
        turnService.playTurn();

        TurnDto turnDto = new TurnDto(turnService.getLastPlayedTurnNumber());
        TurnResponse response = turnDtoAssembler.toResponse(turnDto);

        return Response.status(Response.Status.OK).entity(response).build();
    }

    @Path("/unturn")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response unturn() {
        turnService.unturn();

        TurnDto turnDto = new TurnDto(turnService.getCurrentTurnNumber());
        TurnResponse response = turnDtoAssembler.toResponse(turnDto);

        return Response.status(Response.Status.OK).entity(response).build();
    }

    @Path("/reset")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response reset() {
        turnService.resetGame();

        return Response.status(Response.Status.OK).build();
    }
}
