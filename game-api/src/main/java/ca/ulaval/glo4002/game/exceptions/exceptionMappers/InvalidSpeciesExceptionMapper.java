package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.InvalidSpeciesException;

public class InvalidSpeciesExceptionMapper extends ca.ulaval.glo4002.game.exceptions.ExceptionMapper implements ExceptionMapper<InvalidSpeciesException> {
    @Override
    public Response toResponse(InvalidSpeciesException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_SPECIES";
        exceptionResponse.description = "The specified species is not supported.";
        return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
    }
}
