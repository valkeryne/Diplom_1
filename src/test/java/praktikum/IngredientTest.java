package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(Parameterized.class)
public class IngredientTest {

    public IngredientType type;
    public String name;
    public float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тип: {0}, название {1}, цена {2}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {IngredientType.SAUCE, "Песто", 500f},
                {IngredientType.FILLING, "Котлета", 300f},
                {IngredientType.FILLING, "Салат", 0f},
                {IngredientType.SAUCE, "", -10f},
                {null, null, Float.MAX_VALUE},

        };
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertThat(ingredient.getType())
                .as("Неверный тип ингредиента")
                .isEqualTo(type);
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertThat(ingredient.getName())
                .as("Неверное имя ингредиента")
                .isEqualTo(name);
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertThat(ingredient.getPrice())
                .as("Неверная цена ингредиента")
                .isEqualTo(price);
    }
}
