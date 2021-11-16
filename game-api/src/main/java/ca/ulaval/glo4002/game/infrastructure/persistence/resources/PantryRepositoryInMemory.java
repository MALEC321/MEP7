package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;

public class PantryRepositoryInMemory implements PantryRepository {
    private Pantry pantry;

    public PantryRepositoryInMemory() {
        this.pantry = new Pantry();
    }

    @Override
    public Pantry findPantry() {
        return pantry;
    }

    @Override
    public void save(Pantry pantry) {
        this.pantry = pantry;
    }

    @Override
    public void deleteAll() {
        pantry.clear();
    }
}
