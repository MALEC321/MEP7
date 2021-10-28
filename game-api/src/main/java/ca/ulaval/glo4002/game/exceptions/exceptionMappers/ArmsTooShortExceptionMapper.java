package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.ArmsTooShortException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ArmsTooShortExceptionMapper extends ExceptionResponse implements ExceptionMapper<ArmsTooShortException> {
	@Override
	public Response toResponse(ArmsTooShortException e) {
		ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
		exceptionResponseDto.error = ExceptionErrorAndDescription.ARMS_TOO_SHORT.getError();
		exceptionResponseDto.description = ExceptionErrorAndDescription.ARMS_TOO_SHORT.getDescription();
		return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
	}
}
