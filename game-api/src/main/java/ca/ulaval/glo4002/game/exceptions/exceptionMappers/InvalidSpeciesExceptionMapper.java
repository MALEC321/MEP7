package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.InvalidSpeciesException;

public class InvalidSpeciesExceptionMapper extends ExceptionResponse implements ExceptionMapper<InvalidSpeciesException> {
    @Override
    public Response toResponse(InvalidSpeciesException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.INVALID_SPECIES.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.INVALID_SPECIES.getDescription();
        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
