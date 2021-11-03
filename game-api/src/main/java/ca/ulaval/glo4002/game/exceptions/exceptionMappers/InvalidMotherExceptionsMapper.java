package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.InvalidMotherException;

public class InvalidMotherExceptionsMapper implements ExceptionMapper<InvalidMotherException> {

    @Override
    public Response toResponse(InvalidMotherException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = "INVALID_MOTHER";
        exceptionResponse.description = "Mother must be a female.";

        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
