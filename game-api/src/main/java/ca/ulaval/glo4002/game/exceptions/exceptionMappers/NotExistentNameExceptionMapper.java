package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;

public class NotExistentNameExceptionMapper extends ExceptionResponse implements ExceptionMapper<NotExistentNameException> {
    @Override
    public Response toResponse(NotExistentNameException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.NON_EXISTENT_NAME.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.NON_EXISTENT_NAME.getDescription();
        return exceptionResponse(Response.Status.NOT_FOUND, exceptionResponseDto);
    }
}
