package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.InvalidMotherException;

public class InvalidMotherExceptionMapper extends ca.ulaval.glo4002.game.exceptions.ExceptionMapper implements ExceptionMapper<InvalidMotherException> {

    @Override
    public Response toResponse(InvalidMotherException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_MOTHER";
        exceptionResponse.description = "Mother must be a female.";
        return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
    }
}
