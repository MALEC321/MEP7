package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.application.exceptions.food.NoBurgersLeftException;
import ca.ulaval.glo4002.game.application.exceptions.food.NoSaladsLeftException;
import ca.ulaval.glo4002.game.application.exceptions.food.NoWaterLeftException;

public class OrderForm {
    private int burgerQuantity;
    private int saladQuantity;
    private int waterQuantity;

    public OrderForm(int burgerQuantity, int saladQuantity, int waterQuantity) {
        this.burgerQuantity = burgerQuantity;
        this.saladQuantity = saladQuantity;
        this.waterQuantity = waterQuantity;
    }

    public int getBurgerQuantity() {
        return burgerQuantity;
    }

    public int getSaladQuantity() {
        return saladQuantity;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }

    public void removeBurgerQuantity(int burgerQuantity) {
        this.burgerQuantity -= burgerQuantity;
        if(this.burgerQuantity < 0) {
            throw new NoBurgersLeftException();
        }
    }

    public void removeSaladQuantity(int saladQuantity) {
        this.saladQuantity -= saladQuantity;
        if(this.saladQuantity < 0) {
            throw new NoSaladsLeftException();
        }
    }

    public void removeWaterQuantity(int waterQuantity) {
        this.waterQuantity -= waterQuantity;
        if(this.waterQuantity < 0) {
            throw new NoWaterLeftException();
        }
    }
}
