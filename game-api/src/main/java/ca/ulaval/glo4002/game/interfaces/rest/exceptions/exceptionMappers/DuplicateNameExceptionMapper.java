package ca.ulaval.glo4002.game.interfaces.rest.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.DuplicateNameException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DuplicateNameExceptionMapper extends ExceptionResponse implements ExceptionMapper<DuplicateNameException> {
    @Override
    public Response toResponse(DuplicateNameException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.DUPLICATE_NAME.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.DUPLICATE_NAME.getDescription();
        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
