package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.InvalidGenderException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidGenderExceptionMapper extends ExceptionResponse implements ExceptionMapper<InvalidGenderException> {

    @Override
    public Response toResponse(InvalidGenderException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.INVALID_GENDER.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.INVALID_GENDER.getDescription();
        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
