package ca.ulaval.glo4002.game.application.sumo;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoResponse;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.FightAction;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SumoServiceTest {
    private Dinosaur dinoTest1;
    private Dinosaur dinoTest2;
    private Dinosaur dinoTest3;
    private Herd herd;
    private HerdRepository herdRepository;
    private GameRepository gameRepository;
    private ActionFactory actionFactory;
    private Action fightAction;
    private List<Action> actions;

    @InjectMocks
    private SumoService sumoService;

    @Mock
    private Game game;

    @Mock
    private Turn turn;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        HerdRepositoryInMemory herdRepository = new HerdRepositoryInMemory();
        SpeciesDietsCorrespondances speciesDietCorrespondances = new SpeciesDietsCorrespondances();
        DinosaurFactory dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietCorrespondances);
        ActionFactory actionFactory = new ActionFactory();
        dinoTest1 = dinosaurFactory.createDinosaur("Maxence", 10, "m", "Ankylosaurus");
        dinoTest2 = dinosaurFactory.createDinosaur("Marc-Antoine", 10, "m", "Brachiosaurus");
        dinoTest3 = dinosaurFactory.createDinosaur("Beno", 9, "m", "Diplodocus");
        sumoService = new SumoService(herdRepository, actionFactory, gameRepository);
        actions = new ArrayList<>();

        this.herd = mock(Herd.class);
        this.herdRepository = mock(HerdRepository.class);
        this.actionFactory = mock(ActionFactory.class);
        this.gameRepository = mock(GameRepository.class);
        this.fightAction = mock(FightAction.class);
    }

    @Test
    public void givenTwoDinosaurOfIdenticalStrength_whenPredictingWinner_thenResponseIsTie() {
        SumoResponse sumoResponse;

        sumoResponse = sumoService.predictWinner(dinoTest1, dinoTest2);

        assertEquals(sumoResponse.getPredictedWinner(), "tie");
    }

    @Test
    public void givenTwoDinosaurOfDifferentStrength_whenPredictingWinner_thenResponseIsNameOfHeaviestDino() {
        SumoResponse sumoResponse;

        sumoResponse = sumoService.predictWinner(dinoTest1, dinoTest3);

        assertEquals(sumoResponse.getPredictedWinner(), "Maxence");
    }

    @Test
    public void givenValidDto_whenCallingFightMethod_thenAddFightActionToActionRepository() {
        SumoDto sumoDto = new SumoDto("Maxence", "Beno");
        sumoService = new SumoService(herdRepository, actionFactory, gameRepository);
        when(herdRepository.findHerd()).thenReturn(herd);
        when(herd.findDinosaurByName(sumoDto.getChallenger())).thenReturn(dinoTest1);
        when(herd.findDinosaurByName(sumoDto.getChallengee())).thenReturn(dinoTest2);
        when(gameRepository.findGame()).thenReturn(game);
        when(game.currentTurn()).thenReturn(turn);
        when(actionFactory.createFight(actions, dinoTest1, dinoTest2, herd)).thenReturn(fightAction);

        sumoService.fight(sumoDto);

        verify(gameRepository).findGame();
    }
}