package ca.ulaval.glo4002.game.repositories.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;

public class DinosaurRepositoryInMemory implements DinosaurRepository {
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
