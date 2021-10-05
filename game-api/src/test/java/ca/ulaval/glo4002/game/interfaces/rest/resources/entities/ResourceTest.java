package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResourceTest {

    private Burger burger;
    private Salad salad;
    private Water water;

    @BeforeEach
    void setUp() {
        burger = new Burger(50);
        salad = new Salad(30);
        water = new Water(100);
    }

    /************************ TESTING RESOURCEELEMENTS IMPLEMENTATIONS ************************/

    @Test
    void whenCreatingBurger_shouldExpireIn4() {
        assertEquals(4, burger.getDaysLeft());
    }
    @Test
    void whenCreatingSalad_shouldExpireIn3() {
        assertEquals(3, salad.getDaysLeft());
    }
    @Test
    void whenCreatingWater_shouldExpireIn10() {
        assertEquals(10, water.getDaysLeft());
    }
    @Test
    void whenDecreasingBurgerExpirationDate_shouldExpireIn3() {
        burger.decreaseExpirationDate();
        assertEquals(3, burger.getDaysLeft());
    }
    @Test
    void whenDecreasingSaladExpirationDate_shouldExpireIn2() {
        salad.decreaseExpirationDate();
        assertEquals(2, salad.getDaysLeft());
    }
    @Test
    void whenDecreasingWaterExpirationDate_shouldExpireIn9() {
        water.decreaseExpirationDate();
        assertEquals(9, water.getDaysLeft());
    }

    /************************ TESTING RESOURCEELEMENTS ************************/

    @Test
    void whenRemovingRemovingElementResources_shouldDecreaseQuantity() {
        burger.removeElement(49); // there's 50 at the start
        assertEquals(1, burger.getQuantity());
        burger.removeElement(2);
        assertEquals(0, burger.getQuantity());
    }

}
