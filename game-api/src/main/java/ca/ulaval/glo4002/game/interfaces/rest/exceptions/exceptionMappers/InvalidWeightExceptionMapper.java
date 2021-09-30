package ca.ulaval.glo4002.game.interfaces.rest.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidWeightException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidWeightExceptionMapper extends ExceptionResponse implements ExceptionMapper<InvalidWeightException> {
    @Override
    public Response toResponse(InvalidWeightException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.INVALID_WEIGHT.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.INVALID_WEIGHT.getDescription();
        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
