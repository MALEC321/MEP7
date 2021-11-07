package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.application.exceptions.InvalidSpeciesException;

public class InvalidSpeciesExceptionsMapper implements ExceptionMapper<InvalidSpeciesException> {
    @Override
    public Response toResponse(InvalidSpeciesException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_SPECIES";
        exceptionResponse.description = "The specified species is not supported.";

        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
