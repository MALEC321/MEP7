package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;
import ca.ulaval.glo4002.game.application.exceptions.DinosaurAlreadyParticipatingException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DinosaurAlreadyParticipatingExceptionsMapper implements ExceptionMapper<DinosaurAlreadyParticipatingException> {
	@Override
	public Response toResponse(DinosaurAlreadyParticipatingException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("DINOSAUR_ALREADY_PARTICIPATING", "Dinosaur is already participating.");
		return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
	}
}
