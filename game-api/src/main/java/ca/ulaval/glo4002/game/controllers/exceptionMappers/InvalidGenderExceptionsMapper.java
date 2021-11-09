package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.application.exceptions.InvalidGenderException;

public class InvalidGenderExceptionsMapper implements ExceptionMapper<InvalidGenderException> {
    @Override
    public Response toResponse(InvalidGenderException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_GENDER";
        exceptionResponse.description = "The specified gender must be \"m\" or \"f\".";

       return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
