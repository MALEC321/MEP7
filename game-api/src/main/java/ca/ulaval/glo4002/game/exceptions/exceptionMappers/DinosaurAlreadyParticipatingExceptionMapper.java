package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.DinosaurAlreadyParticipatingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DinosaurAlreadyParticipatingExceptionMapper extends ExceptionResponse implements ExceptionMapper<DinosaurAlreadyParticipatingException> {
	@Override
	public Response toResponse(DinosaurAlreadyParticipatingException e) {
		ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
		exceptionResponseDto.error = ExceptionErrorAndDescription.DINOSAUR_ALREADY_PARTICIPATING.getError();
		exceptionResponseDto.description = ExceptionErrorAndDescription.DINOSAUR_ALREADY_PARTICIPATING.getDescription();
		return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
	}
}
