package ca.ulaval.glo4002.game.controllers.turn.dtos;

import ca.ulaval.glo4002.game.domain.turn.TurnNumber;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnDtoAssemblerTest {
    private final int TURNMUMBER = 3;
    private TurnDtoAssembler turnDtoAssembler;
    private TurnNumber turnNumber;
    private TurnDto turnDto;

    @Test
    public void givenTurnDtoNumber_whentoResponse_thenReturnedTurnNumber() {
        turnNumber = new TurnNumber(TURNMUMBER);
        turnDto = new TurnDto(turnNumber);
        turnDto.getNoTurn();
        turnDtoAssembler = new TurnDtoAssembler();

        turnDtoAssembler.toResponse(turnDto);

        assertEquals(TURNMUMBER, turnNumber.getNumber());
    }
}