package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.CARNIVORE;
import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.HERBIVORE;

public class Herd {

    private Map<String, Dinosaur> dinosaursByName = new HashMap<>();

    public Herd(Herd herd) {
        this.dinosaursByName = herd.dinosaursByName;
    }

    public Herd() {
    }

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

    public String fight(Dinosaur challenger, Dinosaur challengee, boolean isReal) {
        String winner = "";
        if (challenger.getStrength() > challengee.getStrength()) {
            winner = challenger.getName();
            if (isReal) {
                challengee.setIsDead(true);
                challenger.setIsHungry(true);
            }
        } else if (challenger.getStrength() < challengee.getStrength()) {
            winner = challengee.getName();
            if (isReal) {
                challenger.setIsDead(true);
                challengee.setIsHungry(true);
            }
        } else {
            winner = "tie";
            if (isReal) {
                challengee.setIsDead(true);
                challenger.setIsHungry(true);
            }
        }
        return winner;
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

        copiedDinoList.removeIf(dinosaur -> dinosaur.getDietType() == CARNIVORE);
        copiedDinoList.sort(Comparator.comparing(Dinosaur::getStrength).thenComparing(Dinosaur::getName, Comparator.reverseOrder()));

        return copiedDinoList;
    }

    private List<Dinosaur> findSortedCarnivoreAndOmnivore(List<Dinosaur> sortedDinosaursByStrengthThenName) {
        List<Dinosaur> copiedDinoList = new ArrayList<>(sortedDinosaursByStrengthThenName);
        copiedDinoList.removeIf(dinosaur -> dinosaur.getDietType() == HERBIVORE);

        return copiedDinoList;
    }

    public void removeOrphanedBabyDinosaurs() {
        for (Dinosaur dinosaur : findSortedDinosaursByStrengthThenName()) {
            if (dinosaur.isOrphan()) {
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
        ResourcesStateDto totalFoodGiven = foodContainerForHerbivore.add(foodContainerForCarnivore);
        ResourcesStateDto leftOver = resourcesStateDto.minus(totalFoodGiven);
        for (Dinosaur dinosaur : findSortedHerbivoreAndOmnivore()) {
            foodContainerForHerbivore = dinosaur.eat(foodContainerForHerbivore); // S2
        }
        for (Dinosaur dinosaur : findSortedCarnivoreAndOmnivore(findSortedDinosaursByStrengthThenName())) {
            foodContainerForCarnivore = dinosaur.eat(foodContainerForCarnivore);
        }

        // S1 U S2
        return foodContainerForHerbivore.add(foodContainerForCarnivore).add(leftOver); // {burger: 4} {salad: 2}  {water: 3}
    }

    public void removeAllHungryDinosaur() {
        for (Dinosaur dinosaur : findAllDinosaurs()) {
            if (dinosaur.getIsDead()) {
                dinosaursByName.remove(dinosaur.getName());
            }
        }
    }

    public void clear() {
        dinosaursByName.clear();
    }
}
