package ca.ulaval.glo4002.game.interfaces.rest.exceptions;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAllowed implements ExceptionMapper<NotAllowedException> {
    @Override
    public Response toResponse(NotAllowedException e) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}
