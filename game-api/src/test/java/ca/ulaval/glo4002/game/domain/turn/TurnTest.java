package ca.ulaval.glo4002.game.domain.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.ulaval.glo4002.game.application.manager.ZooManager;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.turn.aggregate.TurnId;
import ca.ulaval.glo4002.game.infrastructure.persistence.turn.TurnRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.domain.actions.Action;
import org.mockito.Mock;

public class TurnTest {
    private String random;
    private List<Action> actions;
    private TurnId turnId;
    private TurnRepositoryInMemory turnRepositoryInMemory;
    private  TurnFactory turnFactory;

    @BeforeEach
    void createTurn() {
        turnFactory = new TurnFactory();
        random = UUID.randomUUID().toString().toUpperCase();
        actions = new ArrayList<>();
        turnId = new TurnId(random.substring(0, random.indexOf("-")), actions);
        turnRepositoryInMemory = new TurnRepositoryInMemory();
    }

    @Test
    void whenAsked_returnsCorrectId() {
        assertEquals(random.substring(0, random.indexOf("-")), turnId.getTurnId());
    }

    @Test
    void whenAsked_returnsActionsList() {
        assertEquals(actions, turnId.getActions());
    }

    @Test
    void whenTurnIsPlayed_incrementsTurn() {
        turnFactory.create(random, actions);
        turnRepositoryInMemory.save(turnId);
        turnFactory.create(random, actions);
        turnRepositoryInMemory.save(turnId);

        assertEquals(2, turnRepositoryInMemory.numberOfTurns());
    }
}
