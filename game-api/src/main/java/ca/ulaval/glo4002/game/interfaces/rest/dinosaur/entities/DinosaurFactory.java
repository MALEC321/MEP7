package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.DuplicateNameException;

import java.util.HashMap;
import java.util.Map;

public class DinosaurFactory {
    private final DinosaurRepository dinosaurRepository;

    public DinosaurFactory(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    //Changer diet pour enum?
    private static final Map<String, String> speciesDiet = new HashMap<String, String>() {{
        put("Ankylosaurus", "Herbivore");
        put("Brachiosaurus", "Herbivore");
        put("Diplodocus", "Herbivore");
        put("Stegosaurus", "Herbivore");
        put("Brachiosaurus", "Herbivore");
        put("Triceratops", "Carnivore");
        put("Allosaurus", "Carnivore");
        put("Megalosaurus", "Carnivore");
        put("Tyrannosaurus Rex", "Carnivore");
        put("Velociraptor", "Carnivore");
    }};

    public Dinosaur create(String name, int weight, String gender, String species) {
        validateName(name);
        return new Dinosaur(name, weight, gender, species, speciesDiet.get(species));
    }

    private void validateName(String name) {
        if (dinosaurRepository.findByName(name) != null) {
            throw new DuplicateNameException();
        }
    }
}
