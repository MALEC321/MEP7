package ca.ulaval.glo4002.game.domain.dinosaur.enums;

import java.util.HashMap;
import java.util.Map;

public class SpeciesDietsCorrespondances {
    public static final Map<String, DietType> speciesDictionary = new HashMap<String, DietType>() {{
        put("Ankylosaurus", DietType.HERBIVORE);
        put("Brachiosaurus", DietType.HERBIVORE);
        put("Diplodocus", DietType.HERBIVORE);
        put("Stegosaurus", DietType.HERBIVORE);
        put("Triceratops", DietType.HERBIVORE);
        put("Allosaurus", DietType.CARNIVORE);
        put("Megalosaurus", DietType.CARNIVORE);
        put("Spinosaurus", DietType.CARNIVORE);
        put("Tyrannosaurus Rex", DietType.CARNIVORE);
        put("Velociraptor", DietType.CARNIVORE);
    }};

    public boolean dinosaurExists(String name) {
        return speciesDictionary.containsKey(name);
    }
}
