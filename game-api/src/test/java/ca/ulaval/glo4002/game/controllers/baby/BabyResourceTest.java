package ca.ulaval.glo4002.game.controllers.baby;

import ca.ulaval.glo4002.game.application.baby.BabyUseCase;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyDtoAssembler;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BabyResourceTest extends JerseyTest {
	BabyResponse babyResponse;
	private BabyUseCase babyUseCase;
	private BabyDtoAssembler babyDtoAssembler;

	@Before
	public void setup(){
		babyResponse = new BabyResponse("name", "fatherName", "motherName");
	}

	@Override
	protected Application configure() {
		babyUseCase = Mockito.mock(BabyUseCase.class);
		babyDtoAssembler = Mockito.mock(BabyDtoAssembler.class);
		return new ResourceConfig().register(new BabyResource(babyUseCase, babyDtoAssembler));
	}

	@Test
	public void givenResourceRequest_whenCreateResources_thenReturnedOkStatus(){
		BabyCreationDto babyCreationDto = new BabyCreationDto("name", "fatherName", "motherName");
		when(babyDtoAssembler.fromRequest(anyObject())).thenReturn(babyCreationDto);

		Response response = target("breed").request(MediaType.APPLICATION_JSON_TYPE).post(null);

		verify(babyUseCase).createBaby(babyCreationDto);
		assertEquals(200, response.getStatus());
	}

}