package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class AddDinoTest {
    @InjectMocks
    private AddDino addDino;

    @Mock
    private Dinosaur dinosaur;

    @Mock
    private Herd herd;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenAddDinoAction_whenExecuting_thenAddDinosaurShouldBeCalled() {
        addDino.execute();

        verify(herd).addDinosaur(dinosaur);
    }
}