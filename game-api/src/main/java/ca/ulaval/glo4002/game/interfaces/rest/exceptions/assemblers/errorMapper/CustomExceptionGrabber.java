package ca.ulaval.glo4002.game.interfaces.rest.exceptions.assemblers.errorMapper;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.HTTPExceptionItem;

import javax.ws.rs.core.Response;

public class CustomExceptionGrabber
        implements javax.ws.rs.ext.ExceptionMapper<HTTPExceptionItem> {
    @Override
    public Response toResponse(HTTPExceptionItem e) {
        ExceptionResponseDto res = new ExceptionResponseDto();
        res.code = e.getCode();
        res.message = e.getMessage();
        e.printStackTrace();
        return Response.status(e.getStatus()).entity(res).build();
    }
}
