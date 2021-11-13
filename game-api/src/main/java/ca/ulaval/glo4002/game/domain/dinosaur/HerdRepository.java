package ca.ulaval.glo4002.game.domain.dinosaur;

public interface HerdRepository {
    Herd findHerd();

    void save(Herd herd);

    void deleteAll();
}
