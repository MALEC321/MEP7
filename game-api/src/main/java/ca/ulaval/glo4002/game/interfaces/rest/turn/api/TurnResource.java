package ca.ulaval.glo4002.game.interfaces.rest.turn.api;

import ca.ulaval.glo4002.game.interfaces.rest.turn.api.assemblers.TurnDtoAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.turn.api.dtos.TurnRequest;
import ca.ulaval.glo4002.game.interfaces.rest.turn.api.dtos.TurnResponse;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.TurnUseCase;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnCreationDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/turn")
public class TurnResource {

    private final TurnUseCase turnUseCase;
    private final TurnDtoAssembler turnDtoAssembler;

    public TurnResource(TurnUseCase turnUseCase,
                        TurnDtoAssembler turnDtoAssembler) {
        this.turnUseCase = turnUseCase;
        this.turnDtoAssembler = turnDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTurn(TurnRequest turnRequest) {
        TurnCreationDto dto = turnDtoAssembler.fromRequest(turnRequest);
        turnUseCase.createTurn(dto);

        TurnDto turnDto = new TurnDto();
        turnDto.turnNumber = TurnFactory.number;
        TurnResponse response = turnDtoAssembler.toResponse(turnDto);

        return Response.status(Response.Status.CREATED).entity(response).build();
    }
}
