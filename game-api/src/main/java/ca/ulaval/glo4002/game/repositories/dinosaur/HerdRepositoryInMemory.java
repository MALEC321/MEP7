package ca.ulaval.glo4002.game.repositories.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;

public class HerdRepositoryInMemory implements HerdRepository {
    private Herd herd;

    @Override
    public Herd findHerd() {
        return this.herd;
    }

    @Override
    public void add(Herd herd) {
        this.herd = herd;
    }

    @Override
    public void reset() {
        herd.reset();
    }
}
