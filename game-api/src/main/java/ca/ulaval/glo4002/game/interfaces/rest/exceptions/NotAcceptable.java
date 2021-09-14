package ca.ulaval.glo4002.game.interfaces.rest.exceptions;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAcceptable implements ExceptionMapper<NotAcceptableException> {
    @Override
    public Response toResponse(NotAcceptableException e) {
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
