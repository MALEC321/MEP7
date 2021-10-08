package ca.ulaval.glo4002.game.domain.resources;

import java.util.LinkedList;
import java.util.Queue;

public class Pantry {
    public Queue<Burger> burgerQueue = new LinkedList<>();
    public Queue<Salad> saladQueue = new LinkedList<>();
    public Queue<Water> waterQueue = new LinkedList<>();

    public boolean eatBurger(int quantityNeeded, Resource consumedResources) { //900
        for (ResourceElements burgerBowl: burgerQueue) {
            int actualQuantity = burgerBowl.getQuantity();
            if (burgerBowl.removeElement(quantityNeeded)) {
                consumedResources.addBurger(quantityNeeded);
                return true;
            }
            consumedResources.addBurger(actualQuantity);
            quantityNeeded -= actualQuantity;
        }
        return false;
    }

    public boolean eatSalad(int quantity, Resource consumedResources) {
        for (ResourceElements saladBowl: saladQueue) {
            int actualQuantity = saladBowl.getQuantity();
            if (saladBowl.removeElement(quantity)) {
                consumedResources.addSalad(quantity);
                return true;
            }
            consumedResources.addSalad(actualQuantity);
            quantity -= actualQuantity; //Because salad quantity will be 0 after removeElement
        }
        return false;
    }

    public boolean drinkWater(int quantity, Resource consumedResources) {
        for (ResourceElements waterBank: waterQueue) {
            int actualQuantity = waterBank.getQuantity();
            if (waterBank.removeElement(quantity)) {
                consumedResources.addWater(quantity);
                return true;
            }
            consumedResources.addWater(actualQuantity);
            quantity -= actualQuantity;
        }
        return false;
    }
}
