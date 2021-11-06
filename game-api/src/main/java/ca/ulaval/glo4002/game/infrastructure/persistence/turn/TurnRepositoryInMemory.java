package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import ca.ulaval.glo4002.game.domain.turn.aggregate.TurnId;

public class TurnRepositoryInMemory implements TurnRepository {
    private final List<TurnId> turnsById = new ArrayList<>();

    @Override
    public void save(TurnId turnId) {
        turnsById.add(turnId);
    }

    @Override
    public TurnId findById(String turnId) {
        for (TurnId turnIdFound: turnsById) {
            if (turnIdFound.getTurnId().equals(turnId)) {
                return turnIdFound;
            }
        }
        return null;
    }

    @Override
    public void reset() {
        turnsById.clear();
    }

    @Override
    public String nextTurnId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return random.substring(0, random.indexOf("-"));
    }

    @Override
    public int numberOfTurns() {
        return turnsById.size();
    }
}
