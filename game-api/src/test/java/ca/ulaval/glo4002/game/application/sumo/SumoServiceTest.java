package ca.ulaval.glo4002.game.application.sumo;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoResponse;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.infrastructure.persistence.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumoServiceTest {
    private Dinosaur dinoTest1;
    private Dinosaur dinoTest2;
    private Dinosaur dinoTest3;
    private SumoService sumoService;

    @BeforeEach
    public void setUp() {
        Herd herd = new Herd();
        HerdRepositoryInMemory herdRepository = new HerdRepositoryInMemory();
        SpeciesDietsCorrespondances speciesDietCorrespondances = new SpeciesDietsCorrespondances();
        DinosaurFactory dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietCorrespondances);
        ActionFactory actionFactory = new ActionFactory();
        ActionRepositoryInMemory actionRepository = new ActionRepositoryInMemory();
        dinoTest1 = dinosaurFactory.createDinosaur("Maxence", 10, "m", "Ankylosaurus");
        dinoTest2 = dinosaurFactory.createDinosaur("Marc-Antoine", 10, "m", "Brachiosaurus");
        dinoTest3 = dinosaurFactory.createDinosaur("Beno", 9, "m", "Diplodocus");
        sumoService = new SumoService(herdRepository, actionFactory, actionRepository);
    }

    @Test
    void givenTwoDinosaurOfIdenticalStrength_whenPredictingWinner_thenResponseIsTie(){
        SumoResponse sumoResponse;
        sumoResponse = sumoService.predictWinner(dinoTest1, dinoTest2);
        assertEquals(sumoResponse.getPredictedWinner(), "tie");
    }

    @Test
    void givenTwoDinosaurOfDifferentStrength_whenPredictingWinner_thenResponseIsNameOfHeaviestDino(){
        SumoResponse sumoResponse;
        sumoResponse = sumoService.predictWinner(dinoTest1, dinoTest3);
        assertEquals(sumoResponse.getPredictedWinner(), "Maxence");
    }
}