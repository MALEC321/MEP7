package ca.ulaval.glo4002.game.controllers.dinosaur.dtos;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DinosaurDtoAssemblerTest {
	DinosaurDtoAssembler dinosaurDtoAssembler;
	DinosaurRequest dinosaurRequest;
	DinosaurCreationDto dinosaurCreationDto;
	DinosaurResponseItem dinosaurResponseItem;
	DinosaurDto dinosaurDto;
	DinosaurDto dinosaurDtoUn;
	DinosaurDto dinosaurDtoDeux;
	DinosaursResponse dinosaursResponse;

	@Before
	public void setup(){
		dinosaurDto = new DinosaurDto("name",5,"gender","species");
		dinosaurDtoUn = new DinosaurDto("name",5,"gender","species");
		dinosaurDtoDeux = new DinosaurDto("name",5,"gender","species");
		dinosaurDtoAssembler = new DinosaurDtoAssembler();
		dinosaurRequest = new DinosaurRequest("name",5,"gender","species");
	}


	@Test
	public void givenDinosaurRequest_whenFromRequest_thenReturnedNewDinosaur(){

		dinosaurCreationDto = dinosaurDtoAssembler.fromRequest(dinosaurRequest);

		assertEquals(dinosaurRequest.getName(), dinosaurCreationDto.getName());
		assertEquals(dinosaurRequest.getWeight(), dinosaurCreationDto.getWeight());
		assertEquals(dinosaurRequest.getGender(), dinosaurCreationDto.getGender());
		assertEquals(dinosaurRequest.getSpecies(), dinosaurCreationDto.getSpecies());
	}

	@Test
	public void givenDinosaurDto_whenToResponse_thenReturnedNewDinosaurResponseItem(){

		dinosaurResponseItem = dinosaurDtoAssembler.toResponse(dinosaurDto);

		assertEquals(dinosaurDto.getName(), dinosaurResponseItem.getName());
		assertEquals(dinosaurDto.getWeight(), dinosaurResponseItem.getWeight());
		assertEquals(dinosaurDto.getGender(), dinosaurResponseItem.getGender());
		assertEquals(dinosaurDto.getSpecies(), dinosaurResponseItem.getSpecies());
	}

	@Test
	public void givenDinosaursDtos_whenToResponse_thenReturnedDinosaursResponses(){
		List<DinosaurDto> dinosaurDtos = new ArrayList<>();
		dinosaurDtos.add(dinosaurDtoUn);
		dinosaurDtos.add(dinosaurDtoDeux);

		dinosaursResponse = dinosaurDtoAssembler.toResponse(dinosaurDtos);

		assertEquals(dinosaurDtos.size(), dinosaursResponse.getItems().size());

	}
}