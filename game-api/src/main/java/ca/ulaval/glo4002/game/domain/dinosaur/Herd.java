package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.*;

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
