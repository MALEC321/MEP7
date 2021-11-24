//package ca.ulaval.glo4002.game.application.turn;
//
//import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
//import ca.ulaval.glo4002.game.domain.actions.Action;
//import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
//import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
//import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
//import ca.ulaval.glo4002.game.domain.game.Game;
//import ca.ulaval.glo4002.game.domain.game.GameRepository;
//import ca.ulaval.glo4002.game.domain.resources.*;
//import ca.ulaval.glo4002.game.domain.turn.Turn;
//import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.mockito.Mockito.*;
//
//public class TurnServiceTest {
//    private static final int EXPECTED = 1;
//    private HerdRepository herdRepository;
//    private ResourcesDistributor resourcesDistributor;
//    private TurnFactory turnFactory;
//    private GameRepository gameRepository;
//    private PantryRepository pantryRepository;
//    private ResourcesFactory resourcesFactory;
//    private ActionFactory actionFactory;
//    private TurnService turnService;
//    private Game game;
//    private Pantry pantry;
//    private Herd herd;
//    private Action action;
//    private Resources burgers;
//    private Resources salads;
//    private Resources water;
//    private Turn turn;
//
//
//    @Before
//    public void setUp() {
//
//        game = mock(Game.class);
//        pantry = mock(Pantry.class);
//        herd = mock(Herd.class);
//        gameRepository = mock(GameRepository.class);
//        turnFactory = mock(TurnFactory.class);
//        pantryRepository = mock(PantryRepository.class);
//        herdRepository = mock(HerdRepository.class);
//        resourcesDistributor = mock(ResourcesDistributor.class);
//        resourcesFactory = mock(ResourcesFactory.class);
//        actionFactory = mock(ActionFactory.class);
//        turnService = new TurnService(turnFactory, gameRepository, pantryRepository, herdRepository, resourcesDistributor, resourcesFactory, actionFactory);
//    }
//
//    @Test
//   public void givenATurn_whenGetTurnNumber_thenReturnsCurrentTurnNumber() {
//        when(gameRepository.findGame()).thenReturn(game);
//
//        turnService.getLastPlayedTurnNumber();
//
//        verify(game).lastPlayedTurnNumber();
//    }
//
//    @Test
//    public void givenATurn_whenResetTurn_thenTurnIsReset() {
//        turnService.resetGame();
//
//        verify(gameRepository).deleteAll();
//        verify(pantryRepository).deleteAll();
//        verify(herdRepository).deleteAll();
//    }
//
//    @Test
//<<<<<<< HEAD
//    public void givenAturn_whenExecuteTurn_thenOrphanedDinonaursAreRemoved() {
//        turnService.executeTurn();
//
//        verify(herd).removeOrphanedBabyDinosaurs();
//    }
//
//    private void setTurnContext() {
//        TurnNumber turnNumber = new TurnNumber(1);
//        when(actionRepository.getActionList()).thenReturn(createActionsToExecute());
//        when(pantryRepository.findPantry()).thenReturn(pantry);
//        when(herdRepository.findHerd()).thenReturn(herd);
//        when(resourcesFactory.create(BURGER, TURN_BURGERS_QUANTITY)).thenReturn(burgers);
//        when(resourcesFactory.create(SALAD, TURN_SALADS_QUANTITY)).thenReturn(salads);
//        when(resourcesFactory.create(WATER, TURN_WATER_QUANTITY)).thenReturn(water);
//=======
//    public void whengetLastPlayerTurnNumber_thenReturnedTurnNumber() {
//>>>>>>> develop
//        when(gameRepository.findGame()).thenReturn(game);
//
//<<<<<<< HEAD
//    private List<Action> createActionsToExecute() {
//        Resources resources = new Resources(ResourceType.BURGER, TURN_BURGERS_QUANTITY, ResourceType.BURGER.getExpiration());
//        Action action = new AddResource(resources, pantry);
//        return Arrays.asList(action);
//    }
//=======
//        turnService.getLastPlayedTurnNumber();
//>>>>>>> develop
//
//        verify(game).lastPlayedTurnNumber();
//    }
//
//}