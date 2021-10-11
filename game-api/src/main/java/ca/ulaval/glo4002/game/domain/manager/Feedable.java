package ca.ulaval.glo4002.game.domain.manager;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

public interface Feedable {
    boolean feed(Dinosaur dinosaur);
}
