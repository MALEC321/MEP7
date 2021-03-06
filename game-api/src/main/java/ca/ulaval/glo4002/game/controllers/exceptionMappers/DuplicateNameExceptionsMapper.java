package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.dinosaur.DuplicateNameException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DuplicateNameExceptionsMapper implements ExceptionMapper<DuplicateNameException> {
    @Override
    public Response toResponse(DuplicateNameException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("DUPLICATE_NAME", "The specified name already exists and must be unique.");
        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
