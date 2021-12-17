package ca.ulaval.glo4002.game.domain.resources;

public interface PantryRepository {
    Pantry findCurrentPantry();
    void save(Pantry pantry);
    void deleteAll();

    Pantry findPreviousPantry();

    void deleteLast();
}
