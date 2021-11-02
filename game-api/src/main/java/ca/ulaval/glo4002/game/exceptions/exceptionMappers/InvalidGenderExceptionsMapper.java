package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.ExceptionsMapper;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.InvalidGenderException;

public class InvalidGenderExceptionsMapper extends ExceptionsMapper implements ExceptionMapper<InvalidGenderException> {

    @Override
    public Response toResponse(InvalidGenderException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_GENDER";
        exceptionResponse.description = "The specified gender must be \"m\" or \"f\".";
        return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
    }
}
