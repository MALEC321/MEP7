package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.InvalidResourceQuantityException;

public class InvalidResourceExceptionMapper extends ExceptionResponse implements ExceptionMapper<InvalidResourceQuantityException> {
    @Override
    public Response toResponse(InvalidResourceQuantityException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.INVALID_RESOURCE_QUANTITY.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.INVALID_RESOURCE_QUANTITY.getDescription();

        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
