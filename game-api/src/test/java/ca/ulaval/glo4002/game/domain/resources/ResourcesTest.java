package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourcesTest {
    private ResourcesFactory resourcesFactory;
    private Resources burger;
    private Resources salad;
    private Resources water;

    @BeforeEach
    void setUp() {
        resourcesFactory = new ResourcesFactory();
        burger = resourcesFactory.create(BURGER, 50);
        salad = resourcesFactory.create(SALAD, 30);
        water = resourcesFactory.create(WATER, 100);
    }

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
    void whenRemovingResourceElement_shouldDecreaseQuantity() {
        burger.removeElement(49); // there's 50 at the start
        assertEquals(1, burger.getQuantity());
        burger.removeElement(2);
        assertEquals(0, burger.getQuantity());
    }

    @Test
    void whenResetingResourceElement_quantityShouldEqualZero() {
        salad.reset();
        assertEquals(0, salad.getQuantity());
    }

    @Test
    void whenSettingResourceElementQuantity_shouldChangeQuantity() {
        water.setQuantity(20);
        assertEquals(20, water.getQuantity());
    }

    @Test
    void whenAddingResourceElementQuantity_shouldChangeQuantity() {
        burger.addQuantity(1971);
        assertEquals(2021, burger.getQuantity());
    }
}
