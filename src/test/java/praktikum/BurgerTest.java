package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static praktikum.Constants.PRICE_DELTA;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1;

    @Mock
    Ingredient ingredient2;

    @Test
    public void testSetBun() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertThat(burger.bun)
                .as("Булочка должна быть в бургере")
                .isEqualTo(bun);
    }

    @Test
    public void testSetIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        assertThat(burger.ingredients)
                .as("Ингредиент должен быть в бургере")
                .contains(ingredient1);
    }

    @Test
    public void testMixIngredients() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertThat(burger.ingredients.get(0))
                .as("Ингредиент2 должен быть первым")
                .isEqualTo(ingredient2);
    }

    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertThat(burger.ingredients)
                .as("Ингредиент должен быть удалён")
                .isEmpty();
    }

    @Test
    public void testBurgerPrice() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getPrice()).thenReturn(100.5f);
        when(ingredient2.getPrice()).thenReturn(66.77f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedBurgerPrice = (float) (100 * 2 + 100.5 + 66.77);
        assertThat(burger.getPrice())
                .as("Неверная цена бургера")
                .isCloseTo(expectedBurgerPrice, within(PRICE_DELTA));
    }

    @Test
    public void testLinestringBun() {
        when(bun.getName()).thenReturn("Булочка с кунжутом");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getName()).thenReturn("Сырный соус");
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getPrice()).thenReturn(10f);
        when(ingredient2.getName()).thenReturn("Котлета");
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getPrice()).thenReturn(15f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String expectedString = String.format(
                "(==== Булочка с кунжутом ====)%n" +
                "= sauce Сырный соус =%n" +
                "= filling Котлета =%n" +
                "(==== Булочка с кунжутом ====)%n" +
                "%nPrice: %f%n", 225.0f);
        assertThat(burger.getReceipt())
                .as("Неверный текст бургера")
                .isEqualTo(expectedString);
    }

}
