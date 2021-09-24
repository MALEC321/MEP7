package ca.ulaval.glo4002.game.interfaces.rest.exceptions.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.HTTPExceptionItem;

public class ExceptionDtoAssembler {
    public static ExceptionResponse toResponse(HTTPExceptionItem exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.code = exception.getCode();
        response.message = exception.getMessage();
        return response;
    }
}
