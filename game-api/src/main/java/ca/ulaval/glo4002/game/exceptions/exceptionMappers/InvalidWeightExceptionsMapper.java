package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.ExceptionsMapper;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.InvalidWeightException;

public class InvalidWeightExceptionsMapper extends ExceptionsMapper implements ExceptionMapper<InvalidWeightException> {
    @Override
    public Response toResponse(InvalidWeightException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_WEIGHT";
        exceptionResponse.description = "The specified weight must be greater than 0.";
        return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
    }
}
