package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.resources.InvalidResourceQuantityException;

public class InvalidResourceExceptionsMapper implements ExceptionMapper<InvalidResourceQuantityException> {
    @Override
    public Response toResponse(InvalidResourceQuantityException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_RESOURCE_QUANTITY";
        exceptionResponse.description = "Resource quantities must be positive.";

        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
