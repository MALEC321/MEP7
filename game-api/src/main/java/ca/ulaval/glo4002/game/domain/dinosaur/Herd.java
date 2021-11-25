package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.CARNIVORE;
import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.HERBIVORE;

public class Herd {
    private final Map<String, Dinosaur> dinosaursByName = new HashMap<>();

    public List<Dinosaur> findAllDinosaurs() {
        return new ArrayList<>(dinosaursByName.values());
    }

    public Dinosaur findDinosaurByName(String name) {
        Dinosaur dinosaur = dinosaursByName.get(name);
        return dinosaursByName.get(name);
    }

    public boolean dinosaurExists(String name) {
        if (dinosaursByName.equals(null))
            return false;
        else
            return true;
    }

    public void addDinosaur(Dinosaur dinosaur) {
        dinosaursByName.putIfAbsent(dinosaur.getName(), dinosaur);
    }

    public void removeDinosaur(Dinosaur dinosaur) {
        dinosaursByName.remove(dinosaur.getName());
    }

    public void fight(Dinosaur challenger, Dinosaur challengee) {
        if (challenger.getStrength() > challengee.getStrength()) {
            challengee.setDead(true);
            challenger.setHungry(true);
        } else if (challenger.getStrength() < challengee.getStrength()) {
            challenger.setDead(true);
            challengee.setHungry(true);
        } else {
            challengee.setHungry(true);
            challenger.setHungry(true);
        }
    }

    public List<Dinosaur> findSortedDinosaursByStrengthThenName() {
        List<Dinosaur> copiedDinoList = this.findAllDinosaurs();

        copiedDinoList.sort(Comparator.comparing(Dinosaur::getStrength).reversed().thenComparing(Dinosaur::getName));

        return copiedDinoList;
    }

//    private List<Dinosaur> findSortedHerbivoreAndOmnivore(final List<Dinosaur> sortedDinosaursByStrengthThenName) {
//        List<Dinosaur> copiedDinoList = new ArrayList<>(sortedDinosaursByStrengthThenName);
//        Collections.reverse(copiedDinoList);
//        copiedDinoList.removeIf(dinosaur -> dinosaur.getDiet() == CARNIVORE);
//
//        return copiedDinoList;
//    }

    public List<Dinosaur> findSortedHerbivoreAndOmnivore() {
        List<Dinosaur> copiedDinoList = this.findAllDinosaurs();

        copiedDinoList.removeIf(dinosaur -> dinosaur.getDiet() == CARNIVORE);
        copiedDinoList.sort(Comparator.comparing(Dinosaur::getStrength).thenComparing(Dinosaur::getName, Comparator.reverseOrder()));

        return copiedDinoList;
    }

    private List<Dinosaur> findSortedCarnivoreAndOmnivore(List<Dinosaur> sortedDinosaursByStrengthThenName) {
        List<Dinosaur> copiedDinoList = new ArrayList<>(sortedDinosaursByStrengthThenName);
        copiedDinoList.removeIf(dinosaur -> dinosaur.getDiet() == HERBIVORE);

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

    public ResourcesStateDto feedDinosaurs(final ResourcesStateDto resourcesStateDto) { // {burger: 10} {salad: 10}  {water: 11}
        //{burger: 10} {salad: 0}  {water: 5} GM carnivore {burger:4} {salad: 0}  {water: 2}
        //{burger: 0} {salad: 10}  {water: 5} GM herbivore {burger: 0} {salad: 2}  {water: 1}
        // S1 Split state into states of burger and half water
        // S2 Split state into states of salad and half water
        ResourcesStateDto foodContainerForHerbivore = resourcesStateDto.createFoodContainerForHerbivore();
        ResourcesStateDto foodContainerForCarnivore = resourcesStateDto.createFoodContainerForCarnivore();
        for (Dinosaur dinosaur : findSortedHerbivoreAndOmnivore()) {
            foodContainerForHerbivore = dinosaur.eat(foodContainerForHerbivore); // S2
        }
        for (Dinosaur dinosaur : findSortedCarnivoreAndOmnivore(findSortedDinosaursByStrengthThenName())) {
            foodContainerForCarnivore = dinosaur.eat(foodContainerForCarnivore);
        }

        // S1 U S2
        return foodContainerForHerbivore.add(foodContainerForCarnivore); // {burger: 4} {salad: 2}  {water: 3}
    }

    public void removeAllHungryDinosaur() {
        for (Dinosaur dinosaur : findAllDinosaurs()) {
            if (dinosaur.isDead()) {
                dinosaursByName.remove(dinosaur.getName());
            }
        }
    }

    public void clear() {
        dinosaursByName.clear();
    }
}
