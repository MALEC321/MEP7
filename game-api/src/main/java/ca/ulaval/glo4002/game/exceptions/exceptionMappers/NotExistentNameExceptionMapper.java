package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;

public class NotExistentNameExceptionMapper extends ca.ulaval.glo4002.game.exceptions.ExceptionMapper implements ExceptionMapper<NotExistentNameException> {
    @Override
    public Response toResponse(NotExistentNameException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "NON_EXISTENT_NAME";
        exceptionResponse.description = "The specified name does not exist.";
        return exceptionMapper(Response.Status.NOT_FOUND, exceptionResponse);
    }
}
