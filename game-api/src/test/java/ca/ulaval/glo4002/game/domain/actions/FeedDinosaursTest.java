package ca.ulaval.glo4002.game.domain.actions;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;

public class FeedDinosaursTest {

    @InjectMocks
    private Action feedDinosaurs;

    @Mock
    private HerdRepository herdRepository;

    @Mock
    private Pantry pantry;

    @Mock
    private Herd herd;

    @BeforeEach
    void init() {
        feedDinosaurs = new FeedDinosaurs(pantry, herd);
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void givenFeedDinosaursAction_whenExecuting_thenFeedDinosaursShouldBeCalled() {
//        feedDinosaurs.execute();
//
//        verify(resourcesDistributor).feedDinosaurs(pantry, herd);
//    }

}