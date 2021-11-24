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
    public void setUp() {
        resourcesFactory = new ResourcesFactory();
        burger = resourcesFactory.create(BURGER, 50);
        salad = resourcesFactory.create(SALAD, 30);
        water = resourcesFactory.create(WATER, 100);
    }

    @Test
    public void whenCreatingBurger_thenExpireIn4() {
        assertEquals(4, burger.getDaysLeft());
    }

    @Test
    public void whenCreatingSalad_thenExpireIn3() {
        assertEquals(3, salad.getDaysLeft());
    }

    @Test
    public void whenCreatingWater_thenExpireIn10() {
        assertEquals(10, water.getDaysLeft());
    }

    @Test
    public void whenDecreasingBurgerExpirationDate_thenExpireIn3() {
        burger.decreaseExpirationDate();

        assertEquals(3, burger.getDaysLeft());
    }

    @Test
    public void whenDecreasingSaladExpirationDate_thenExpireIn2() {
        salad.decreaseExpirationDate();

        assertEquals(2, salad.getDaysLeft());
    }

    @Test
    public void whenDecreasingWaterExpirationDate_thenExpireIn9() {
        water.decreaseExpirationDate();

        assertEquals(9, water.getDaysLeft());
    }

    @Test
    public void given50Burgers_whenRemoving49_thenQuantityDecreasedTo1() {
        burger.removeElement(49);

        assertEquals(1, burger.getQuantity());
    }

    @Test
    public void given50Burgers_whenRemovingMoreThanCapacity_thenQuantityDecreasedTo0() {
        burger.removeElement(100);

        assertEquals(0, burger.getQuantity());
    }

    @Test
    public void whenResetingResourceElement_thenQuantityEqualZero() {
        salad.reset();

        assertEquals(0, salad.getQuantity());
    }

    @Test
    public void whenSettingResourceElementQuantity_thenChangeQuantity() {
        water.setQuantity(20);

        assertEquals(20, water.getQuantity());
    }

    @Test
    public void whenAddingResourceElementQuantity_thenChangeQuantity() {
        burger.addQuantity(1971);

        assertEquals(2021, burger.getQuantity());
    }
}
