package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.actions.AddResource;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.*;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static ca.ulaval.glo4002.game.application.turn.TurnService.*;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TurnServiceTest {
    private static final int EXPECTED = 1;
    private HerdRepository herdRepository;
    private ResourcesDistributor resourcesDistributor;
    private TurnFactory turnFactory;
    private ActionRepository actionRepository;
    private GameRepository gameRepository;
    private PantryRepository pantryRepository;
    private ResourcesFactory resourcesFactory;
    private TurnService turnService;
    private Game game;
    private Pantry pantry;
    private Herd herd;
    private Resources burgers;
    private Resources salads;
    private Resources water;


    @Before
    public void setUp() {
        setResources();
        game = mock(Game.class);
        pantry = mock(Pantry.class);
        herd = mock(Herd.class);
        gameRepository = mock(GameRepository.class);
        turnFactory = mock(TurnFactory.class);
        pantryRepository = mock(PantryRepository.class);
        herdRepository = mock(HerdRepository.class);
        actionRepository = mock(ActionRepository.class);
        resourcesDistributor = mock(ResourcesDistributor.class);
        resourcesFactory = mock(ResourcesFactory.class);
        turnService = new TurnService(turnFactory, gameRepository, pantryRepository, herdRepository, actionRepository,resourcesDistributor, resourcesFactory);
        setTurnContext();
    }

    @Test
   public void givenATurn_whenGetTurnNumber_thenReturnsCurrentTurnNumber(){
        TurnNumber turnNumber = new TurnNumber(1);
        when(gameRepository.findGame()).thenReturn(game);
        when(game.currentTurnNumber()).thenReturn(turnNumber);

        assertEquals(EXPECTED, turnService.getTurnNumber().getNumber());
    }

    @Test
    public void givenATurn_whenResetTurn_thenTurnIsReset(){
        turnService.resetGame();

        verify(gameRepository).deleteAll();
        verify(pantryRepository).deleteAll();
        verify(herdRepository).deleteAll();
        verify(actionRepository).deleteAll();
    }

    @Test
    public void givenAturn_whenExecuteTurn_thenTurnConclusionsAreExecuted(){
        turnService.executeTurn();

        verify(actionRepository).getActionList();
    }

    @Test
    public void givenAturn_whenExecuteTurn_thenPantryIsFilledWithTurnDefaultResourcesNumber(){
        turnService.executeTurn();

        verify(pantry).addResources(burgers);
        verify(pantry).addResources(salads);
        verify(pantry).addResources(water);
    }

    @Test
    public void givenAturn_whenExecuteTurn_thenDinosaursAreFed(){
        turnService.executeTurn();

        verify(resourcesDistributor).feedDinosaurs(pantry, herd);
    }

    @Test
    public void givenAturn_whenExecuteTurn_thenOrphanedDinonaursAreRemoved() {
        turnService.executeTurn();

        verify(herd).removeOrphanedBabyDinosaurs();
    }

    private void setTurnContext() {
        TurnNumber turnNumber = new TurnNumber(1);
        when(actionRepository.getActionList()).thenReturn(createActionsToExecute());
        when(pantryRepository.findPantry()).thenReturn(pantry);
        when(herdRepository.findHerd()).thenReturn(herd);
        when(resourcesFactory.create(BURGER, TURN_BURGERS_QUANTITY)).thenReturn(burgers);
        when(resourcesFactory.create(SALAD, TURN_SALADS_QUANTITY)).thenReturn(salads);
        when(resourcesFactory.create(WATER, TURN_WATER_QUANTITY)).thenReturn(water);
        when(gameRepository.findGame()).thenReturn(game);
        when(game.nextTurnNumber()).thenReturn(turnNumber);
        when(turnFactory.create(any(), any())).thenReturn(new Turn(turnNumber));
    }

    private List<Action> createActionsToExecute() {
        Resources resources = new Resources(ResourceType.BURGER, TURN_BURGERS_QUANTITY, ResourceType.BURGER.getExpiration());
        Action action = new AddResource(resources, pantry);
        return Arrays.asList(action);
    }

    private void setResources() {
        burgers = new Resources(BURGER, TURN_BURGERS_QUANTITY, BURGER.getExpiration());
        salads = new Resources(SALAD, TURN_SALADS_QUANTITY, SALAD.getExpiration());
        water = new Resources(WATER, TURN_WATER_QUANTITY, WATER.getExpiration());
    }

}