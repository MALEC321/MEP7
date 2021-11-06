package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.exceptions.InvalidWeightException;

public class InvalidWeightExceptionsMapper implements ExceptionMapper<InvalidWeightException> {
    @Override
    public Response toResponse(InvalidWeightException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_WEIGHT";
        exceptionResponse.description = "The specified weight must be greater than 0.";

        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
