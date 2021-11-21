package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidSpeciesException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidSpeciesExceptionsMapper implements ExceptionMapper<InvalidSpeciesException> {
    @Override
    public Response toResponse(InvalidSpeciesException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("INVALID_SPECIES","The specified species is not supported.");
        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
