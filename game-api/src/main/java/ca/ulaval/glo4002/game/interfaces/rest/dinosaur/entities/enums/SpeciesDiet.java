package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums;

public enum SpeciesDiet {
  Ankylosaurus("Ankylosaurus", "Herbivore"),
  Brachiosaurus("Brachiosaurus", "Herbivore"),
  Diplodocus("Diplodocus", "Herbivore"),
  Stegosaurus("Stegosaurus", "Herbivore"),
  Triceratops("Triceratops", "Herbivore"),
  Allosaurus("Allosaurus", "Carnivore"),
  Megalosaurus("Megalosaurus", "Carnivore"),
  Spinosauraus("Spinosauraus", "Carnivore"),
  Tyrannosaurus("Tyrannosaurus Rex", "Carnivore"),
  Velociraptor("Velociraptor", "Carnivore");

  private final String specie;
  private final String dietType;

  SpeciesDiet(String specie, String dietType) {
    this.specie = specie;
    this.dietType = dietType;
  }

  public String getDietType() {
    return dietType;
  }
}
