package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums;

public enum DietType {
  HERBIVORE("Herbivore"),
  CARNIVORE("Carnivore");

  private final String dietType;

  DietType(String dietType) {
    this.dietType = dietType;
  }
}
