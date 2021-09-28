package ca.ulaval.glo4002.game.interfaces.rest.exceptions.errorMapper;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionResponseDto;

import javax.ws.rs.core.Response;

public class UnknownExceptionGrabber
        implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        ExceptionResponseDto res = new ExceptionResponseDto();
        res.code = "UNKNOWN_ERROR";
        res.message = "INTERNAL SERVER ERROR";
        e.printStackTrace();
        return Response.status(500).entity(res).build();
    }
}
