package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        if(speciesDictionary.containsKey(name)){
            return true;
        }
        return false;
    }
}
