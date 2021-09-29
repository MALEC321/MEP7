package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums;

public enum SpeciesDiet {
    Ankylosaurus("Ankylosaurus", DietType.HERBIVORE.name()),
    Brachiosaurus("Brachiosaurus", DietType.HERBIVORE.name()),
    Diplodocus("Diplodocus", DietType.HERBIVORE.name()),
    Stegosaurus("Stegosaurus", DietType.HERBIVORE.name()),
    Triceratops("Triceratops", DietType.CARNIVORE.name()),
    Allosaurus("Allosaurus", DietType.CARNIVORE.name()),
    Megalosaurus("Megalosaurus", DietType.CARNIVORE.name()),
    Spinosauraus("Spinosauraus", DietType.CARNIVORE.name()),
    Tyrannosaurus("Tyrannosaurus Rex", DietType.CARNIVORE.name()),
    Velociraptor("Velociraptor", DietType.CARNIVORE.name());

    public final String specy;
    private final String dietType;

    SpeciesDiet(String specy, String dietType) {
        this.specy = specy;
        this.dietType = dietType;
    }

    public static boolean contains(String s)
    {
        for(SpeciesDiet choice:values())
            if (choice.specy.equals(s))
                return true;
        return false;
    }

}

