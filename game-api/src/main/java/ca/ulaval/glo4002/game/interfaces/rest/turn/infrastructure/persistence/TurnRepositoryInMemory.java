package ca.ulaval.glo4002.game.interfaces.rest.turn.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.Turn;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TurnRepositoryInMemory implements TurnRepository {
    private final Map<UUID, Turn> turnsById = new HashMap<>();

    @Override
    public void save(Turn turn) {
        turnsById.put(turn.getId(), turn);
    }

    @Override
    public Turn findById(UUID id) {
        return turnsById.get(id);
    }
}
