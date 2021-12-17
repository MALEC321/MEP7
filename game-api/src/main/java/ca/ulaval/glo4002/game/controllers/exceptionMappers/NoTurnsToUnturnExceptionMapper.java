package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.turn.NoTurnsToUnturnException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoTurnsToUnturnExceptionMapper implements ExceptionMapper<NoTurnsToUnturnException> {

    @Override
    public Response toResponse(NoTurnsToUnturnException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("NO_TURNS_TO_UNTURN","There are no turns to unturn.");
        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
