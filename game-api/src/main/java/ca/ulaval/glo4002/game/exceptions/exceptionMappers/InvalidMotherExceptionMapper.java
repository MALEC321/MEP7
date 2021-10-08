package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.InvalidMotherException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidMotherExceptionMapper extends ExceptionResponse implements ExceptionMapper<InvalidMotherException> {

    @Override
    public Response toResponse(InvalidMotherException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.INVALID_MOTHER.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.INVALID_MOTHER.getDescription();
        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
