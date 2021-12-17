package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;

import java.util.ArrayList;
import java.util.List;

public class HerdRepositoryInMemory implements HerdRepository {
    private List<Herd> herds = new ArrayList<>();

    @Override
    public Herd findPreviousHerd() {
        return herds.get(herds.size()-2);
    }

    @Override
    public void save(Herd herd) {
        this.herds.add(herd);
    }

    @Override
    public Herd findCurrentHerd(){
        return herds.get(herds.size()-1);
    }

    @Override
    public void deleteAll() {
        herds = new ArrayList<>();
    }
}
