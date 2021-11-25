package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.dinosaur.NotExistentNameException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotExistentNameExceptionsMapper implements ExceptionMapper<NotExistentNameException> {
    @Override
    public Response toResponse(NotExistentNameException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("NON_EXISTENT_NAME","The specified name does not exist.");
        return Response.status(Response.Status.NOT_FOUND).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
