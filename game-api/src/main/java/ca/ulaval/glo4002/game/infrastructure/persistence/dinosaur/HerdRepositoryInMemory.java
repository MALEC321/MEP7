package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;

import java.util.ArrayList;
import java.util.List;

public class HerdRepositoryInMemory implements HerdRepository {
    private List<Herd> herds = new ArrayList<>();

    public HerdRepositoryInMemory() {
        this.herds.add(new Herd());
    }

    @Override
    public void save(Herd herd) {
        this.herds.add(herd);
    }

    @Override
    public Herd findCurrent(){
        return this.herds.get(herds.size()-1);
    }

    @Override
    public Herd findPrevious() {
        return this.herds.get(herds.size()-2);
    }


    @Override
    public void resetCurrent() {
        herds.remove(herds.size()-1);
        herds.add(new Herd());
    }
}
