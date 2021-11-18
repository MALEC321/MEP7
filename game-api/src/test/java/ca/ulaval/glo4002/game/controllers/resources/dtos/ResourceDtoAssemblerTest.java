package ca.ulaval.glo4002.game.controllers.resources.dtos;


import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceDtoAssemblerTest {
	ResourcesResponse resourcesResponse;
	ResourceRequest resourceRequest;
	ResourceCreationDto resourceCreationDto;
	ResourcesDto resourcesDto;
	ResourceResponse resourceResponse;
	ResourcesDto resourcesDtoUn;
	ResourcesDto resourcesDtoDeux;
	ResourcesDto resourcesDtoTrois;
	ResourcesAssembler resourcesAssembler;

	ResourceDtoAssembler resourceDtoAssembler;


	@Before
	public void setup(){
		resourceRequest = new ResourceRequest(5,6,8);
		resourcesDto = new ResourcesDto(15,16,18);
		resourcesDtoUn = new ResourcesDto(15,6,8);
		resourcesDtoDeux = new ResourcesDto(5,16,18);
		resourcesDtoTrois = new ResourcesDto(5,16,18);
		resourceDtoAssembler = new ResourceDtoAssembler();
		resourcesAssembler = new ResourcesAssembler();
	}

	@Test
	public void givenResourceRequest_whenFromRequest_thenReturnedNewResource(){

		resourceCreationDto = resourceDtoAssembler.fromRequest(resourceRequest);

		assertEquals(resourceRequest.getQtyBurger(), resourceCreationDto.getQtyBurger());
		assertEquals(resourceRequest.getQtySalad(), resourceCreationDto.getQtySalad());
		assertEquals(resourceRequest.getQtyWater(), resourceCreationDto.getQtyWater());
	}

	@Test
	public void givenResourcesDto_whenToResponse_thenReturnedNewResourceResponse(){

		resourceResponse = resourceDtoAssembler.toResponse(resourcesDto);

		assertEquals(resourcesDto.getQtyBurger(), resourceResponse.getQtyBurger());
		assertEquals(resourcesDto.getQtySalad(), resourceResponse.getQtySalad());
		assertEquals(resourcesDto.getQtyWater(), resourceResponse.getQtyWater());
	}

	@Test
	public void givenResourcesDtos_whenToResponse_thenReturnedResourceResponses(){
		List<ResourcesDto> resourcesDtos = new ArrayList<>();
		resourcesDtos.add(resourcesDtoUn);
		resourcesDtos.add(resourcesDtoDeux);
		resourcesDtos.add(resourcesDtoTrois);

		resourcesResponse = resourceDtoAssembler.toResponse(resourcesDtos);

		assertEquals(resourcesDtos.get(0).getQtyBurger(), resourcesResponse.getFresh().getQtyBurger());
		assertEquals(resourcesDtos.get(1).getQtySalad(), resourcesResponse.getConsumed().getQtySalad());
		assertEquals(resourcesDtos.get(2).getQtyWater(), resourcesResponse.getExpired().getQtyWater());

	}

}