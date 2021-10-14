package ca.ulaval.glo4002.game.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;

public abstract class ExceptionResponse {
    public Response exceptionResponse(final Response.Status status, final ExceptionResponseDto exceptionResponseDto) {
        return Response.status(status).entity(exceptionResponseDto).type(MediaType.APPLICATION_JSON).build();
    }
}
