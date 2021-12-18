package ca.ulaval.glo4002.game.domain.dinosaur;

public interface HerdRepository {

    Herd findCurrent();

    Herd findPrevious();

    void save(Herd herd);

    void deleteAll();
}
