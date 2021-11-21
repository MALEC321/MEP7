package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidFatherExceptionsMapper implements ExceptionMapper<InvalidFatherException> {
    @Override
    public Response toResponse(InvalidFatherException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("INVALID_FATHER", "Father must be a male.");
        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
