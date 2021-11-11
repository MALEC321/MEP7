package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidBabyWeightChangeException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidBabyWeightChangeExceptionMapper implements ExceptionMapper<InvalidBabyWeightChangeException> {
	@Override
	public Response toResponse(InvalidBabyWeightChangeException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.error = "INVALID_BABY_WEIGHT_CHANGE";
		exceptionResponse.description = "The weight of a baby dinosaur can not be changed.";

		return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
	}
}
