package ca.ulaval.glo4002.game.controllers.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurResponseItem;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaursResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DinosaurResourceTest extends JerseyTest {
    private DinosaursResponse dinosaursResponse;
    private DinosaurUseCase dinosaurUseCase;
    private DinosaurDtoAssembler dinosaurDtoAssembler;
    private DinosaurResponseItem dinosaurResponseItem;

    @Before
    public void setup() {
        List<DinosaurResponseItem> items = new ArrayList<>();
        dinosaursResponse = new DinosaursResponse(items);
        dinosaurResponseItem = new DinosaurResponseItem("dino", 1, "gender", "species");
    }

    @Override
    protected Application configure() {
        dinosaurUseCase = mock(DinosaurUseCase.class);
        dinosaurDtoAssembler = mock(DinosaurDtoAssembler.class);
        return new ResourceConfig().register(new DinosaurResource(dinosaurUseCase, dinosaurDtoAssembler));
    }

    @Test
    public void givenDinosaurRequest_whenCreateDinosaur_thenReturnedOkStatus() {
        DinosaurCreationDto dinosaurCreationDto = new DinosaurCreationDto("dino", 1, "gender", "species");
        when(dinosaurDtoAssembler.fromRequest(anyObject())).thenReturn(dinosaurCreationDto);

        Response response = target("dinosaurs").request(MediaType.APPLICATION_JSON_TYPE).post(null);

        verify(dinosaurUseCase).createDinosaur(dinosaurCreationDto);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void whenGetAllDinosaurs_thenReturnedOkStatus() {
        List<DinosaurDto> dinosaurDtos = mock(List.class);
        when(dinosaurUseCase.getAllDinosaurs()).thenReturn(dinosaurDtos);
        when(dinosaurDtoAssembler.toResponse(dinosaurDtos)).thenReturn(dinosaursResponse);

        Response response = target("dinosaurs").request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void givenDinosaurName_whenGetDinosaur_thenReturnedOkStatus() {
        String name = "dino";
        DinosaurDto dinosaurDto = dinosaurUseCase.getDinosaur(name);
        when(dinosaurDtoAssembler.toResponse(dinosaurDto)).thenReturn(dinosaurResponseItem);

        Response response = target("dinosaurs/name").request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
    }
}