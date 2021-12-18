package ca.ulaval.glo4002.game.domain.resources;

public interface PantryRepository {
    Pantry findCurrent();
    void save(Pantry pantry);
    void deleteAll();

    Pantry findPrevious();
    public void deleteLast();
}
