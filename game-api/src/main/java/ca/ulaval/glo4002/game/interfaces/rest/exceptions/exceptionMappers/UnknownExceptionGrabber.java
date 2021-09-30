package ca.ulaval.glo4002.game.interfaces.rest.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.dtos.ExceptionResponseDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class UnknownExceptionGrabber implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        ExceptionResponseDto res = new ExceptionResponseDto();
        res.error = "UNKNOWN_ERROR";
        res.description = "INTERNAL SERVER ERROR";
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}
