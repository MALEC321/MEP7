package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.resources.InvalidResourceQuantityException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidResourceExceptionsMapper implements ExceptionMapper<InvalidResourceQuantityException> {
    @Override
    public Response toResponse(InvalidResourceQuantityException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("INVALID_RESOURCE_QUANTITY", "Resource quantities must be positive.");
        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
