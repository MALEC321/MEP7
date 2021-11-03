package ca.ulaval.glo4002.game.application.turn;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

public class TurnUseCaseTest {

    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private Pantry pantry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(resourceRepository.getPantry()).thenReturn(pantry);
    }
}