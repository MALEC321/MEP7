package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.resources.Pantry;

import java.util.*;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class Herd {
    private final Map<String, Dinosaur> dinosaursByName = new HashMap<>();

    public List<Dinosaur> findAll() {
        return new ArrayList<>(dinosaursByName.values());
    }

    public Dinosaur findByName(String name) {
        return dinosaursByName.get(name);
    }

    public void add(Dinosaur dinosaur) {
        dinosaursByName.putIfAbsent(dinosaur.getName(), dinosaur);
    }

    public void remove(Dinosaur dinosaur) {
        dinosaursByName.remove(dinosaur.getName());
    }

    public void feedDinosaurs(Pantry pantry) {
        for (Dinosaur dinosaur : getSortedDinosaursByStrengthThenName()) {
            if (!pantry.removeResource(SALAD, dinosaur.calculateSaladsNeeds())) dinosaur.setStarving(true);
            if (!pantry.removeResource(BURGER, dinosaur.calculateBurgersNeeds())) dinosaur.setStarving(true);
            if (!pantry.removeResource(WATER, dinosaur.calculateWaterNeeds())) dinosaur.setStarving(true);

            if (dinosaur.isStarving()) {
                dinosaursByName.remove(dinosaur.getName());
            }
        }
    }

    public List<Dinosaur> getSortedDinosaursByStrengthThenName() {
        List<Dinosaur> copiedDinoList = this.findAll();

        copiedDinoList.sort(Comparator.comparing(Dinosaur::getStrength).reversed().thenComparing(Dinosaur::getName));

        return copiedDinoList;
    }

    public boolean areBothParentsDead(Dinosaur dinosaur) {
        return dinosaursByName.get(((DinosaurBaby) dinosaur).getFatherName()) == null
                && dinosaursByName.get(((DinosaurBaby) dinosaur).getMotherName()) == null;
    }

    public void reset() {
        dinosaursByName.clear();
    }
}
