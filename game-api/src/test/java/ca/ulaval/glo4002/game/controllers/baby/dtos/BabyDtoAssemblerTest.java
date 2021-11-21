package ca.ulaval.glo4002.game.controllers.baby.dtos;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BabyDtoAssemblerTest {
	BabyRequest babyRequest;
	BabyDtoAssembler babyDtoAssembler;
	BabyDto babyDto;
	BabyCreationDto babyCreationDto;
	BabyResponse babyResponse;

	@Before
	public void setup(){
		babyDtoAssembler = new BabyDtoAssembler();
		babyDto = new BabyDto("baby", "fatherName", "motherName");
		babyRequest = new BabyRequest("baby", "fatherName", "motherName");
		babyCreationDto = new BabyCreationDto("baby", "fatherName", "motherName");
		babyResponse = new BabyResponse("baby", "fatherName", "motherName");
	}

	@Test
	public void givenBabyRequest_whenFromRequest_thenReturnedNewBaby(){

		babyDtoAssembler.fromRequest(babyRequest);

		assertEquals(babyRequest.getName(), babyCreationDto.getName());
		assertEquals(babyRequest.getFatherName(), babyCreationDto.getFatherName());
		assertEquals(babyRequest.getMotherName(), babyCreationDto.getMotherName());
	}

	@Test
	public void givenBabyDto_whenToResponse_thenReturnedNewBabyResponse(){

		babyDtoAssembler.toResponse(babyDto);

		assertEquals(babyDto.getName(), babyResponse.getName());
		assertEquals(babyDto.getFatherName(), babyResponse.getFatherName());
		assertEquals(babyDto.getMotherName(), babyResponse.getMotherName());
	}


}