package ca.ulaval.glo4002.game.application.baby.dtos;

import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BabyAssemblerTest {

	@Test
	public void givenFatherSpeciesAndMotherSpecies_whenToExternalDto_thenReturnedRequestBreed(){
		BabyAssembler babyAssembler = new BabyAssembler();
		String MOPTHERSPECIES = "MotherSpecies";
		String FATHERSPECIES = "FatherSpecies";
		RequestBreed requestBreed = babyAssembler.toExternalDto(FATHERSPECIES, MOPTHERSPECIES);
		assertNotNull(requestBreed);
	}


}