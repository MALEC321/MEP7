package ca.ulaval.glo4002.game.controllers.sumo.dtos;

import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SumoDtoAssemblerTest {

	@Test
	 public void givenSumoRequest_whenFromRequest_thenReturnedSumoDto(){
		SumoDtoAssembler sumoDtoAssembler = new SumoDtoAssembler();
		String CHALLENGEE = "Challengee";
		String CHALLENGER = "Challenger";
		SumoRequest sumoRequest = new SumoRequest(CHALLENGER, CHALLENGEE);
		SumoDto sumoDto = sumoDtoAssembler.fromRequest(sumoRequest);

		assertEquals(sumoRequest.getChallenger(), sumoDto.getChallenger());
		assertEquals(sumoRequest.getChallengee(), sumoDto.getChallengee());
	 }
}