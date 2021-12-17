package ca.ulaval.glo4002.game.domain.dinosaur;

public interface HerdRepository {
    Herd findPreviousHerd();

    Herd findCurrentHerd();

    void save(Herd herd);

    void deleteAll();
}
