package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.*;

public class Herd {
    private final Map<String, Dinosaur> dinosaursByName = new HashMap<>();

    public List<Dinosaur> findAllDinosaurs() {
        return new ArrayList<>(dinosaursByName.values());
    }

    public Dinosaur findDinosaurByName(String name) {
        return dinosaursByName.get(name);
    }

    public void addDinosaur(Dinosaur dinosaur) {
        dinosaursByName.putIfAbsent(dinosaur.getName(), dinosaur);
    }

    public void removeDinosaur(Dinosaur dinosaur) {
        dinosaursByName.remove(dinosaur.getName());
    }

    public List<Dinosaur> findSortedDinosaursByStrengthThenName() {
        List<Dinosaur> copiedDinoList = this.findAllDinosaurs();

        copiedDinoList.sort(Comparator.comparing(Dinosaur::getStrength).reversed().thenComparing(Dinosaur::getName));

        return copiedDinoList;
    }

    public void removeOrphanedBabyDinosaurs() {
        // Todo make this recursive !
        for (Dinosaur dinosaur : findSortedDinosaursByStrengthThenName()) {
            if (dinosaur.areBothParentsDead()) {
                removeDinosaur(dinosaur);
            }
        }
    }

    public void clear() {
        dinosaursByName.clear();
    }
}
