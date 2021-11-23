package ca.ulaval.glo4002.game.application.sumo;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoResponse;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.actions.FightAction;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.infrastructure.persistence.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SumoServiceTest {
    private Dinosaur dinoTest1;
    private Dinosaur dinoTest2;
    private Dinosaur dinoTest3;
    private SumoService sumoService;

    private Herd herd;
    private HerdRepository herdRepository;
    private ActionFactory actionFactory;
    private ActionRepository actionRepository;
    private Action fightAction;
    private SumoDto sumoDto;
    private List<Action> actions;

    @BeforeEach
    public void setUp() {
        //Pour créer les dinos
        HerdRepositoryInMemory herdRepository = new HerdRepositoryInMemory();
        SpeciesDietsCorrespondances speciesDietCorrespondances = new SpeciesDietsCorrespondances();
        DinosaurFactory dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietCorrespondances);
        ActionFactory actionFactory = new ActionFactory();
        ActionRepositoryInMemory actionRepository = new ActionRepositoryInMemory();
        dinoTest1 = dinosaurFactory.createDinosaur("Maxence", 10, "m", "Ankylosaurus");
        dinoTest2 = dinosaurFactory.createDinosaur("Marc-Antoine", 10, "m", "Brachiosaurus");
        dinoTest3 = dinosaurFactory.createDinosaur("Beno", 9, "m", "Diplodocus");
        sumoService = new SumoService(herdRepository, actionFactory, actionRepository);
        actions = new ArrayList<>();

        //Pour les tests
        this.herd = mock(Herd.class);
        this.herdRepository = mock(HerdRepository.class);
        this.actionFactory = mock(ActionFactory.class);
        this.actionRepository = mock(ActionRepository.class);
        this.fightAction = mock(FightAction.class);}

    @Test
    void givenTwoDinosaurOfIdenticalStrength_whenPredictingWinner_thenResponseIsTie() {
        SumoResponse sumoResponse;
        sumoResponse = sumoService.predictWinner(dinoTest1, dinoTest2);
        assertEquals(sumoResponse.getPredictedWinner(), "tie");
    }

    @Test
    void givenTwoDinosaurOfDifferentStrength_whenPredictingWinner_thenResponseIsNameOfHeaviestDino() {
        SumoResponse sumoResponse;
        sumoResponse = sumoService.predictWinner(dinoTest1, dinoTest3);
        assertEquals(sumoResponse.getPredictedWinner(), "Maxence");
    }

    @Test
    void givenValidDto_whenCallingFightMethod_thenAddFightActionToActionRepository() {
        sumoDto = new SumoDto("Maxence", "Beno");
        sumoService = new SumoService(herdRepository, actionFactory, actionRepository);

        when(herdRepository.findHerd()).thenReturn(herd);
        when(herd.findDinosaurByName(sumoDto.getChallenger())).thenReturn(dinoTest1);
        when(herd.findDinosaurByName(sumoDto.getChallengee())).thenReturn(dinoTest2);
        when(actionFactory.createFight(actions, dinoTest1, dinoTest2, herd)).thenReturn(fightAction);

        sumoService.fight(sumoDto);
        verify(actionRepository).save(fightAction);
    }
}