package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.DinosaurAlreadyParticipatingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DinosaurAlreadyParticipatingExceptionMapper extends ca.ulaval.glo4002.game.exceptions.ExceptionMapper implements ExceptionMapper<DinosaurAlreadyParticipatingException> {
	@Override
	public Response toResponse(DinosaurAlreadyParticipatingException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.error = "DINOSAUR_ALREADY_PARTICIPATING";
		exceptionResponse.description = "Dinosaur is already participating.";
		return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
	}
}
