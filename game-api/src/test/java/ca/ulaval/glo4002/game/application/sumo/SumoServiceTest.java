package ca.ulaval.glo4002.game.application.sumo;

import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SumoServiceTest {
    private Dinosaur dinoTest1;
    private Dinosaur dinoTest2;
    private Dinosaur dinoTest3;
//    private SumoDto sumoDto;
    private List<Action> actions;
    private Herd realHerd;

    @InjectMocks
    private SumoService sumoService;

    @Mock
    private Herd herd;
    @Mock
    private HerdRepository herdRepository;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private ActionFactory actionFactory;
    @Mock
    private Action fightAction;
    @Mock
    private Game game;
    @Mock
    private Turn turn;
    @Mock
    private SumoDto sumoDto;
    @Mock
    private Dinosaur challenger;
    @Mock
    private Dinosaur challengee;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

//        HerdRepositoryInMemory herdRepository = new HerdRepositoryInMemory();
//        SpeciesDietsCorrespondances speciesDietCorrespondances = new SpeciesDietsCorrespondances();
//        DinosaurFactory dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietCorrespondances);
//        ActionFactory actionFactory = new ActionFactory();
//        realHerd = new Herd();
//        dinoTest1 = dinosaurFactory.createDinosaur("Maxence", 10, "m", "Ankylosaurus");
//        dinoTest2 = dinosaurFactory.createDinosaur("Marc-Antoine", 10, "m", "Brachiosaurus");
//        dinoTest3 = dinosaurFactory.createDinosaur("Beno", 9, "m", "Diplodocus");
//        realHerd.addDinosaur(dinoTest1);
//        realHerd.addDinosaur(dinoTest2);
//        realHerd.addDinosaur(dinoTest3);
//        sumoService = new SumoService(herdRepository, actionFactory, gameRepository);
//        actions = new ArrayList<>();

//        this.herd = mock(Herd.class);
//        this.herdRepository = mock(HerdRepository.class);
//        this.actionFactory = mock(ActionFactory.class);
//        this.gameRepository = mock(GameRepository.class);
//        this.fightAction = mock(FightAction.class);
    }

//    @Test
//    void givenTwoDinosaurOfIdenticalStrength_whenPredictingWinner_thenResponseIsTie() {
//        sumoDto = new SumoDto("Maxence", "Marc-Antoine");
//        sumoService = new SumoService(herdRepository, actionFactory, gameRepository);
//
//        when(herdRepository.findCurrentHerd()).thenReturn(realHerd);
//        when(gameRepository.findCurrentGame()).thenReturn(game);
//        when(game.currentTurn()).thenReturn(turn);
//        when(actionFactory.createFight(actions, dinoTest1, dinoTest2, realHerd)).thenReturn(fightAction);
//
//        assertEquals(sumoService.fight(sumoDto).getPredictedWinner(), "tie");
//    }
//
//    @Test
//    void givenTwoDinosaurOfDifferentStrength_whenPredictingWinner_thenResponseIsNameOfHeaviestDino() {
//        sumoDto = new SumoDto("Maxence", "Beno");
//        sumoService = new SumoService(herdRepository, actionFactory, gameRepository);
//
//        when(herdRepository.findCurrentHerd()).thenReturn(realHerd);
//        when(gameRepository.findCurrentGame()).thenReturn(game);
//        when(game.currentTurn()).thenReturn(turn);
//        when(actionFactory.createFight(actions, dinoTest1, dinoTest2, realHerd)).thenReturn(fightAction);
//
//        assertEquals(sumoService.fight(sumoDto).getPredictedWinner(), "Maxence");
//    }
//
//    @Test
//    void givenValidDto_whenCallingFightMethod_thenAddFightActionToActionRepository() {
//        sumoDto = new SumoDto("Maxence", "Beno");
//        sumoService = new SumoService(herdRepository, actionFactory, gameRepository);
//        when(herdRepository.findCurrentHerd()).thenReturn(herd);
//        when(herd.findDinosaurByName(sumoDto.getChallenger())).thenReturn(dinoTest1);
//        when(herd.findDinosaurByName(sumoDto.getChallengee())).thenReturn(dinoTest2);
//        when(gameRepository.findCurrentGame()).thenReturn(game);
//        when(game.currentTurn()).thenReturn(turn);
//        when(actionFactory.createFight(actions, dinoTest1, dinoTest2, herd)).thenReturn(fightAction);
//
//        sumoService.fight(sumoDto);
//
//        verify(gameRepository).findCurrentGame();
//    }

    @Test
    void givenSumoDto_whenFight_thenAddFightActionToTurn() {
        when (herdRepository.findCurrentHerd()).thenReturn(herd);
        when (gameRepository.findCurrentGame()).thenReturn(game);
        when(game.currentTurn()).thenReturn(turn);
        when(actionFactory.createFight(turn.getActions(), challenger, challengee, herd)).thenReturn(fightAction);
        sumoService.fight(sumoDto);

        verify(turn).addAction(fightAction);
    }

    @Test
    void givenSumoDto_whenFight_thenSaveGameToGameRepository() {
        when (herdRepository.findCurrentHerd()).thenReturn(herd);
        when (gameRepository.findCurrentGame()).thenReturn(game);
        when(game.currentTurn()).thenReturn(turn);
        when(actionFactory.createFight(turn.getActions(), challenger, challengee, herd)).thenReturn(fightAction);
        sumoService.fight(sumoDto);

        verify(gameRepository).save(game);
    }

    @Test
    void givenSumoDto_whenFight_thenSumoResponseContainsThePredictedWinner() {
        when (herdRepository.findCurrentHerd()).thenReturn(herd);
        when (gameRepository.findCurrentGame()).thenReturn(game);
        when(game.currentTurn()).thenReturn(turn);
        when(actionFactory.createFight(turn.getActions(), challenger, challengee, herd)).thenReturn(fightAction);
        when(herd.fight(challenger, challengee, false)).thenReturn("nameOfWinner");

        assertEquals("nameOfWinner", sumoService.fight(sumoDto).getPredictedWinner());
    }
}