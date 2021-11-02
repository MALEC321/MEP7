package ca.ulaval.glo4002.game.exceptions;

import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class ExceptionsMapper {
    public Response exceptionMapper(final Response.Status status, final ExceptionResponse exceptionResponse) {
        return Response.status(status).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}




