package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.InvalidResourceTypeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidResourceTypeExceptionMapper extends ExceptionResponse implements ExceptionMapper<InvalidResourceTypeException> {

    @Override
    public Response toResponse(InvalidResourceTypeException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.INVALID_RESOURCE_QUANTITY.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.INVALID_RESOURCE_QUANTITY.getDescription();
        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
