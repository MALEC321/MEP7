package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.InvalidFatherException;

public class InvalidFatherExceptionMapper extends ExceptionResponse implements ExceptionMapper<InvalidFatherException> {

    @Override
    public Response toResponse(InvalidFatherException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.INVALID_FATHER.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.INVALID_FATHER.getDescription();
        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
