package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import java.util.UUID;

public class DinosaurFactory {
    public DinosaurFactory() {}

    public Dinosaur create(String name, int weight, String gender, String species) {
        return new Dinosaur(UUID.randomUUID(), name, weight, gender, species);
    }
}
