package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;

public class HerdRepositoryInMemory implements HerdRepository {
    private Herd herd;

    public HerdRepositoryInMemory() {
        this.herd = new Herd();
    }

    @Override
    public Herd findHerd() {
        return this.herd;
    }

    @Override
    public void save(Herd herd) {
        this.herd = herd;
    }

    @Override
    public void reset() {
        herd = new Herd();
    }
}
