package ca.ulaval.glo4002.game.interfaces.rest.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidResourceQuantityException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidResourceExceptionMapper extends ExceptionResponse implements ExceptionMapper<InvalidResourceQuantityException> {
    @Override
    public Response toResponse(InvalidResourceQuantityException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.INVALID_RESOURCE_QUANTITY.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.INVALID_RESOURCE_QUANTITY.getDescription();

        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
