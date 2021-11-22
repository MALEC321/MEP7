package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class CookItTest {

    @InjectMocks
    private Action cookItAction;

    @Mock
    private ResourcesFactory resourcesFactory;

    @Mock
    private Pantry pantry;

    @BeforeEach
    void init() {
        cookItAction = new CookIt(pantry, resourcesFactory);
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void givenAddDinoAction_whenExecuting_thenAddDinosaurShouldBeCalled() {
//        cookItAction.execute();
//
//        verify(cookItAction);
//    }
}