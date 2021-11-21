package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidGenderException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidGenderExceptionsMapper implements ExceptionMapper<InvalidGenderException> {
    @Override
    public Response toResponse(InvalidGenderException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("INVALID_GENDER", "The specified gender must be \"m\" or \"f\".");
       return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
