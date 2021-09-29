package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums;

import java.util.Dictionary;
import java.util.Hashtable;

public class SpeciesDietsCorrespondences {
    public static final Dictionary<String, String> speciesDictionary = new Hashtable<>() { {
            put("Ankylosaurus", "Herbivore");
            put("Brachiosaurus", "Herbivore");
            put("Diplodocus", "Herbivore");
            put("Stegosaurus", "Herbivore");
            put("Triceratops", "Herbivore");
            put("Allosaurus", "Carnivore");
            put("Megalosaurus", "Carnivore");
            put("Spinosauraus", "Carnivore");
            put("Tyrannosaurus Rex", "Carnivore");
            put("Velociraptor", "Carnivore");
    } };
}
