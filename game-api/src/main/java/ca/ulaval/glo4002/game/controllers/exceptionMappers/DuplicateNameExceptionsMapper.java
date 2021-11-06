package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.exceptions.DuplicateNameException;

public class DuplicateNameExceptionsMapper implements ExceptionMapper<DuplicateNameException> {
    @Override
    public Response toResponse(DuplicateNameException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "DUPLICATE_NAME";
        exceptionResponse.description = "The specified name already exists and must be unique.";

        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
