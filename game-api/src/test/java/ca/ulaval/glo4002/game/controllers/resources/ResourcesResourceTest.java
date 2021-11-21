package ca.ulaval.glo4002.game.controllers.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesUseCase;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceResponse;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourcesResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResourcesResourceTest extends JerseyTest {
    private ResourcesUseCase resourcesUseCase;
    private ResourceDtoAssembler resourceDtoAssembler;
    private ResourceCreationDto resourceCreationDto;
    private ResourceResponse fresh;
    private ResourceResponse expired;
    private ResourceResponse consumed;
    private ResourcesResponse resourcesResponse;

    @Before
    public void setup() {
        resourcesResponse = new ResourcesResponse(fresh, expired, consumed);
    }

    @Override
    protected Application configure() {
        resourcesUseCase = Mockito.mock(ResourcesUseCase.class);
        resourceDtoAssembler = Mockito.mock(ResourceDtoAssembler.class);
        resourcesResponse = Mockito.mock(ResourcesResponse.class);
        return new ResourceConfig().register(new ResourcesResource(resourcesUseCase, resourceDtoAssembler));
    }

    @Test
    public void givenResourceRequest_whenCreateResources_thenReturnedOkStatus() {
        resourceCreationDto = new ResourceCreationDto(2, 2, 2);
        when(resourceDtoAssembler.fromRequest(anyObject())).thenReturn(resourceCreationDto);

        Response response = target("resources").request(MediaType.APPLICATION_JSON_TYPE).post(null);

        verify(resourcesUseCase).createResources(resourceCreationDto);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void givenResourceRequest_whenGetAllResources_thenReturnedOkStatus() {
        List<ResourcesDto> resourcesDtos = new ArrayList<>();
        when(resourcesUseCase.getAllResources()).thenReturn(resourcesDtos);
        when(resourceDtoAssembler.toResponse(resourcesDtos)).thenReturn(resourcesResponse);

        Response response = target("resources").request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
    }
}