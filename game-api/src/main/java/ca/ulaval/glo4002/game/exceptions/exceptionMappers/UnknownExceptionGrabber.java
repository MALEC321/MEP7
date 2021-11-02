package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;

public class UnknownExceptionGrabber implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        ExceptionResponse res = new ExceptionResponse();
        res.error = "UNKNOWN_ERROR";
        res.description = "INTERNAL SERVER ERROR";
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}
