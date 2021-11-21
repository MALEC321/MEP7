package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class RemoveAllExpiredResourcesActionTest {

    @InjectMocks
    private Action removeAllExpiredResources;

    @Mock
    private Pantry pantry;

    @BeforeEach
    void init() {
        removeAllExpiredResources = new RemoveAllExpiredResourcesAction(pantry);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenRemoveAllExpiredResourcesAction_whenExecuting_thenRemoveAllExpiredResourcesShouldBeCalled() {
        removeAllExpiredResources.execute();

        verify(pantry).removeAllExpiredResources();
    }
}