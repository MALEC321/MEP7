package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class UnknownExceptionGrabber implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("UNKNOWN_ERROR","INTERNAL SERVER ERROR");
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exceptionResponse).build();
    }
}
