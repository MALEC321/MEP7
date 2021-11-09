package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;

public class NotExistentNameExceptionsMapper implements ExceptionMapper<NotExistentNameException> {
    @Override
    public Response toResponse(NotExistentNameException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "NON_EXISTENT_NAME";
        exceptionResponse.description = "The specified name does not exist.";

        return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
