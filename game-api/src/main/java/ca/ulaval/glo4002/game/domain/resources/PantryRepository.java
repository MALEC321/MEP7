package ca.ulaval.glo4002.game.domain.resources;

public interface PantryRepository {
    Pantry findPantry();
    void deleteAll();
    void save(Pantry pantry);
}
