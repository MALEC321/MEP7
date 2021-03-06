package ca.ulaval.glo4002.game.controllers.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurService;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurResponse;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaursResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Before;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class DinosaurResourceTest{
    private DinosaursResponse dinosaursResponse;
    private DinosaurService dinosaurService;
    private DinosaurDtoAssembler dinosaurDtoAssembler;
    private DinosaurResponse dinosaurResponse;
    Application application;

    @Before
    public void setup() {
        List<DinosaurResponse> items = new ArrayList<>();
        dinosaursResponse = new DinosaursResponse(items);
        dinosaurResponse = new DinosaurResponse("dino", 1, "gender", "species");
        dinosaurService = mock(DinosaurService.class);
        dinosaurDtoAssembler = mock(DinosaurDtoAssembler.class);
        application = new ResourceConfig().register(new DinosaurResource(dinosaurService, dinosaurDtoAssembler));

    }

//    @Test
//    public void givenDinosaurRequest_whenCreateDinosaur_thenReturnedOkStatus() {
//        DinosaurCreationDto dinosaurCreationDto = new DinosaurCreationDto("dino", 1, "gender", "species");
//        when(dinosaurDtoAssembler.fromRequest(anyObject())).thenReturn(dinosaurCreationDto);
//
//        Response response = target("dinosaurs").request(MediaType.APPLICATION_JSON_TYPE).post(null);
//
//        verify(dinosaurService).createDinosaur(dinosaurCreationDto);
//        assertEquals(200, response.getStatus());
//    }
//
//    @Test
//    public void whenGetAllDinosaurs_thenReturnedOkStatus() {
//        List<DinosaurDto> dinosaurDtos = mock(List.class);
//        when(dinosaurService.getAllDinosaurs()).thenReturn(dinosaurDtos);
//        when(dinosaurDtoAssembler.toResponse(dinosaurDtos)).thenReturn(dinosaursResponse);
//
//        Response response = target("dinosaurs").request(MediaType.APPLICATION_JSON_TYPE).get();
//
//        verify(dinosaurService).getAllDinosaurs();
//        assertEquals(200, response.getStatus());
//    }
//
//    @Test
//    public void givenDinosaurName_whenGetDinosaur_thenReturnedOkStatus() {
//        String name = "dino";
//        DinosaurDto dinosaurDto = dinosaurService.getDinosaur(name);
//        when(dinosaurDtoAssembler.toResponse(dinosaurDto)).thenReturn(dinosaurResponse);
//
//        Response response = target("dinosaurs/name").request(MediaType.APPLICATION_JSON_TYPE).get();
//
//        verify(dinosaurService).getDinosaur(name);
//        assertEquals(200, response.getStatus());
//    }
}