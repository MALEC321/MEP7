package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.ExceptionsMapper;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.DuplicateNameException;

public class DuplicateNameExceptionsMapper extends ExceptionsMapper implements ExceptionMapper<DuplicateNameException> {
    @Override
    public Response toResponse(DuplicateNameException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "DUPLICATE_NAME";
        exceptionResponse.description = "The specified name already exists and must be unique.";
        return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
    }
}
