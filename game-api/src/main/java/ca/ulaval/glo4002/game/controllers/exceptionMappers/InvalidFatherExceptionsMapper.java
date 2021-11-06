package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.dinosaur.InvalidFatherException;

public class InvalidFatherExceptionsMapper implements ExceptionMapper<InvalidFatherException> {

    @Override
    public Response toResponse(InvalidFatherException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_FATHER";
        exceptionResponse.description = "Father must be a male.";

        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
