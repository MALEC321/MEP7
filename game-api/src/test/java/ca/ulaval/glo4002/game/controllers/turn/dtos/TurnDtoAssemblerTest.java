package ca.ulaval.glo4002.game.controllers.turn.dtos;

import ca.ulaval.glo4002.game.domain.turn.TurnNumber;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurnDtoAssemblerTest {
	private TurnResponse turnResponse;
	private TurnDtoAssembler turnDtoAssembler;
	private TurnNumber turnNumber;
	private TurnDto turnDto;
	private final int TURNMUMBER = 3;


	@Test
	public void givenTurnDtoNumber_whentoResponse_thenReturnedTurnNumber() {
		turnNumber = new TurnNumber(TURNMUMBER);
		turnDto = new TurnDto(turnNumber);
		turnDto.getNoTurn();
		turnDtoAssembler = new TurnDtoAssembler();

		turnResponse = turnDtoAssembler.toResponse(turnDto);

		assertEquals(TURNMUMBER, turnNumber.getNumber());
	}

}