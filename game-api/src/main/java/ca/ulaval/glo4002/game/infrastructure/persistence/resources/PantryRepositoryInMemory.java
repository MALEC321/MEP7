package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;

import java.util.ArrayList;
import java.util.List;

public class PantryRepositoryInMemory implements PantryRepository {
    private List<Pantry> pantries = new ArrayList<>();

    public PantryRepositoryInMemory() {
        this.pantries.add(new Pantry());
    }

    @Override
    public Pantry findCurrent() {
        return pantries.get(pantries.size()-1);
    }

    @Override
    public void save(Pantry pantry) {
        this.pantries.add(pantry);
    }

    @Override
    public void deleteAll() {
        pantries = new ArrayList<>();
        pantries.add(new Pantry());
    }

    @Override
    public Pantry findPrevious() {
        return pantries.get(pantries.size()-2);
    }
}
