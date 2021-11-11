package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidWeightChangeException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidWeightChangeExceptionMapper implements ExceptionMapper<InvalidWeightChangeException> {
	@Override
	public Response toResponse(InvalidWeightChangeException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.error = "INVALID_WEIGHT_CHANGE";
		exceptionResponse.description = "The specified weight loss must not make the dinosaur weight less than 100 kg.";

		return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
	}
}
