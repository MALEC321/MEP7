package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.DuplicateNameException;

public class DinosaurFactory {
    private final DinosaurRepository dinosaurRepository;

    public DinosaurFactory(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    public Dinosaur create(String name, int weight, String gender, String species) {
        validateName(name);
        return new Dinosaur(name, weight, gender, species);
    }

    private void validateName(String name) {
        if(dinosaurRepository.findByName(name) != null){
            System.out.println("Invalide Name");
        }
    }
}
