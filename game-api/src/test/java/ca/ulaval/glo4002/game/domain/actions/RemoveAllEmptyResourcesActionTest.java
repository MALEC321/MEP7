package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class RemoveAllEmptyResourcesActionTest {

    @InjectMocks
    private Action removeAllEmptyResources;

    @Mock
    private Pantry pantry;

    @BeforeEach
    void init() {
        removeAllEmptyResources = new RemoveAllEmptyResourcesAction(pantry);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenRemoveAllEmptyResourcesAction_whenExecuting_thenRemoveAllEmptyResourcesShouldBeCalled() {
        removeAllEmptyResources.execute();

        verify(pantry).removeAllEmptyResources();
    }
}