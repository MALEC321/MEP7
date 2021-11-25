////package ca.ulaval.glo4002.game.application.turn;
////
////import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
////import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
////import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
////import ca.ulaval.glo4002.game.domain.actions.Action;
////import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
////import ca.ulaval.glo4002.game.domain.actions.AddResource;
////import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
////import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
////import ca.ulaval.glo4002.game.domain.resources.Pantry;
////import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
////import org.junit.jupiter.api.BeforeEach;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////import org.mockito.MockitoAnnotations;
////
////import static org.mockito.Mockito.when;
////
////public class TurnServiceTest {
////
////    @Mock
////    private PantryRepository resourceRepository;
////    @Mock
////    private HerdRepository herdRepository;
////    @Mock
////    private ResourcesDistributor resourcesDistributor;
////    @Mock
////    private Pantry pantry;
////
//////    private final Dinosaur dinosaurTest = new Dinosaur("DinoTest", 1, "m", "Ankylosaurus", );
////
////    @InjectMocks
////    private TurnService turnService;
////
////    @BeforeEach
////    void setUp() {
////        MockitoAnnotations.initMocks(this);
////        when(resourceRepository.findPantry()).thenReturn(pantry);
////    }
////
////    /*    @Test
////    void whenDinosaurIsStarving_ThenDinosaurShouldBeRemove() {
////        List<Dinosaur> dinosaurs = new ArrayList<>(List.of(dinosaurTest));
////        //when(zooManager.feedDinosaur(pantry, dinosaurTest));
////        turnUseCase.feedDinosaursByDietType(dinosaurs);
////=======
////    private TurnFactory turnFactory;
////    private GameRepository gameRepository;
////    private PantryRepository pantryRepository;
////    private ResourcesFactory resourcesFactory;
////    private ActionFactory actionFactory;
////    private TurnService turnService;
////    private Game game;
////    private Pantry pantry;
////    private Herd herd;
////    private Resources burgers;
////    private Resources salads;
////    private Resources water;
////
////
////    @Before
////    public void setUp() {
////        //setResources();
////        game = mock(Game.class);
////        pantry = mock(Pantry.class);
////        herd = mock(Herd.class);
////        gameRepository = mock(GameRepository.class);
////        turnFactory = mock(TurnFactory.class);
////        pantryRepository = mock(PantryRepository.class);
////        herdRepository = mock(HerdRepository.class);
////        resourcesDistributor = mock(ResourcesDistributor.class);
////        resourcesFactory = mock(ResourcesFactory.class);
////        actionFactory = mock(ActionFactory.class);
////        turnService = new TurnService(turnFactory, gameRepository, pantryRepository, herdRepository, resourcesDistributor, resourcesFactory, actionFactory);
////        //setTurnContext();
////    }
////
/////*    @Test
////   public void givenATurn_whenGetTurnNumber_thenReturnsCurrentTurnNumber() {
////        TurnNumber turnNumber = new TurnNumber(1);
////        when(gameRepository.findGame()).thenReturn(game);
////        when(game.currentTurnNumber()).thenReturn(turnNumber);
////
////        assertEquals(EXPECTED, turnService.TurnNumber().getNumber());
////    }*/
////
////    @Test
////    public void givenATurn_whenResetTurn_thenTurnIsReset() {
////        turnService.resetGame();
////
////        verify(gameRepository).deleteAll();
////        verify(pantryRepository).deleteAll();
////        verify(herdRepository).deleteAll();
////    }
////
////    @Test
////    public void givenAturn_whenExecuteTurn_thenTurnConclusionsAreExecuted() {
////        turnService.playTurn();
////
////
////    }
////
////    @Test
////    public void givenAturn_whenExecuteTurn_thenPantryIsFilledWithTurnDefaultResourcesNumber() {
////        turnService.playTurn();
////
////        verify(pantry).addResources(burgers);
////        verify(pantry).addResources(salads);
////        verify(pantry).addResources(water);
////    }
////
////    @Test
////    public void givenAturn_whenExecuteTurn_thenDinosaursAreFed() {
////        turnService.playTurn();
////
////        verify(resourcesDistributor).feedDinosaurs(pantry, herd);
////    }
////
////    @Test
////    public void givenAturn_whenExecuteTurn_thenOrphanedDinonaursAreRemoved() {
////        turnService.playTurn();
////
////        verify(herd).removeOrphanedBabyDinosaurs();
////    }
////
/////*    private void setTurnContext() {
////        TurnNumber turnNumber = new TurnNumber(1);
////        when(pantryRepository.findPantry()).thenReturn(pantry);
////        when(herdRepository.findHerd()).thenReturn(herd);
////        when(resourcesFactory.create(BURGER, TURN_BURGERS_QUANTITY)).thenReturn(burgers);
////        when(resourcesFactory.create(SALAD, TURN_SALADS_QUANTITY)).thenReturn(salads);
////        when(resourcesFactory.create(WATER, TURN_WATER_QUANTITY)).thenReturn(water);
////        when(gameRepository.findGame()).thenReturn(game);
////        when(game.nextTurnNumber()).thenReturn(turnNumber);
////        when(turnFactory.create(any(), any())).thenReturn(new Turn(turnNumber));
////    }
////
////    private void setResources() {
////        burgers = new Resources(BURGER, TURN_BURGERS_QUANTITY, BURGER.getExpiration());
////        salads = new Resources(SALAD, TURN_SALADS_QUANTITY, SALAD.getExpiration());
////        water = new Resources(WATER, TURN_WATER_QUANTITY, WATER.getExpiration());
////    }*/
////>>>>>>> develop
////
////        verify(dinosaurRepository, times(1)).remove(dinosaurTest);
////    }*/
////}
//=======
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
//    public void whengetLastPlayerTurnNumber_thenReturnedTurnNumber() {
//        when(gameRepository.findGame()).thenReturn(game);
//
//        turnService.getLastPlayedTurnNumber();
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
//    public void whengetLastPlayerTurnNumber_thenReturnedTurnNumber() {
//        when(gameRepository.findGame()).thenReturn(game);
//
//        turnService.getLastPlayedTurnNumber();

//
//        verify(game).lastPlayedTurnNumber();
//    }
//
//}
