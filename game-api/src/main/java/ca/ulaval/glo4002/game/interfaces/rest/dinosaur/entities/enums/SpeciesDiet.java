package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums;

public enum SpeciesDiet {
  Ankylosaurus("Ankylosaurus", DietType.HERBIVORE.name()),
  Brachiosaurus("Brachiosaurus", DietType.HERBIVORE.name()),
  Diplodocus("Diplodocus", DietType.HERBIVORE.name()),
  Stegosaurus("Stegosaurus", DietType.HERBIVORE.name()),
  Triceratops("Triceratops", DietType.CARNIVORE.name()),
  Allosaurus("Allosaurus", DietType.CARNIVORE.name()),
  Megalosaurus("Megalosaurus", DietType.CARNIVORE.name()),
  Spinosauraus("Megalosaurus", DietType.CARNIVORE.name()),
  Tyrannosaurus("Tyrannosaurus Rex", DietType.CARNIVORE.name()),
  Velociraptor("Velociraptor", DietType.CARNIVORE.name());

  private final String specy;
  private final String dietType;

  SpeciesDiet(String specy, String dietType) {
    this.specy = specy;
    this.dietType = dietType;
  }
}
