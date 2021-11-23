package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class FeedDinosaursTest {

    @InjectMocks
    private Action feedDinosaurs;

    @Mock
    private ResourcesDistributor resourcesDistributor;

    @Mock
    private Pantry pantry;

    @Mock
    private Herd herd;

    @BeforeEach
    void init() {
        feedDinosaurs = new FeedDinosaurs(resourcesDistributor, pantry, herd);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenFeedDinosaursAction_whenExecuting_thenFeedDinosaursShouldBeCalled() {
        feedDinosaurs.execute();

        verify(resourcesDistributor).feedDinosaurs(pantry, herd);
    }

}