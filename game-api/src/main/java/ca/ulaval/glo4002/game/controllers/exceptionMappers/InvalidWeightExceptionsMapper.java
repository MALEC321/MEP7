package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.dinosaur.InvalidWeightException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidWeightExceptionsMapper implements ExceptionMapper<InvalidWeightException> {
    @Override
    public Response toResponse(InvalidWeightException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("INVALID_WEIGHT", "The specified weight must be greater than 0.");
        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}