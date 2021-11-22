package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class RemoveOrphanedBabyDinosaursTest {

    @InjectMocks
    private Action removeOrphanedBabyDinosaurs;

    @Mock
    private Herd herd;

    @BeforeEach
    void init() {
        removeOrphanedBabyDinosaurs = new RemoveOrphanedBabyDinosaurs(herd);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenRemoveOrphanedBabyDinosaursAction_whenExecuting_thenRemoveOrphanedBabyDinosaursShouldBeCalled() {
        removeOrphanedBabyDinosaurs.execute();

        verify(herd).removeOrphanedBabyDinosaurs();
    }
}