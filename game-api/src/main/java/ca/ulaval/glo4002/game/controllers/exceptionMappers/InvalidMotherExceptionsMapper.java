package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.baby.InvalidMotherException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidMotherExceptionsMapper implements ExceptionMapper<InvalidMotherException> {
    @Override
    public Response toResponse(InvalidMotherException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse( "INVALID_MOTHER","Mother must be a female.");
        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
