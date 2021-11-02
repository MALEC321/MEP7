package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.ExceptionsMapper;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.InvalidFatherException;

public class InvalidFatherExceptionsMapper extends ExceptionsMapper implements ExceptionMapper<InvalidFatherException> {

    @Override
    public Response toResponse(InvalidFatherException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_FATHER";
        exceptionResponse.description = "Father must be a male.";
        return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
    }
}
