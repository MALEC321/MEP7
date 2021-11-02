package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.InvalidResourceQuantityException;

public class InvalidResourceExceptionMapper extends ca.ulaval.glo4002.game.exceptions.ExceptionMapper implements ExceptionMapper<InvalidResourceQuantityException> {
    @Override
    public Response toResponse(InvalidResourceQuantityException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_RESOURCE_QUANTITY";
        exceptionResponse.description = "Resource quantities must be positive.";

        return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
    }
}
