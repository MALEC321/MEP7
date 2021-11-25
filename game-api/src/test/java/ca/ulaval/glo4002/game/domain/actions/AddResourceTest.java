package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class AddResourceTest {
    @InjectMocks
    private AddResource addResource;

    @Mock
    private Resources resources;

    @Mock
    private Pantry pantry;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenAddResourceAction_whenExecuting_thenAddResourcesShouldBeCalled() {
        addResource.execute();

        verify(pantry).addResources(resources);
    }
}