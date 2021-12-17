package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;

import java.util.ArrayList;
import java.util.List;

public class PantryRepositoryInMemory implements PantryRepository {
    private List<Pantry> pantries = new ArrayList<>();

    @Override
    public Pantry findCurrentPantry() {
        return pantries.get(pantries.size()-1);
    }

    @Override
    public void save(Pantry pantry) {
        this.pantries.add(pantry);
    }

    @Override
    public void deleteAll() {
        pantries = new ArrayList<>();
    }

    @Override
    public Pantry findPreviousPantry() {
        return pantries.get(pantries.size()-2);
    }

    @Override
    public void deleteLast() {
        pantries.remove(pantries.size()-1);
    }
}
