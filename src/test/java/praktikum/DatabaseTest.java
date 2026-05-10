package praktikum;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAvailableBuns() {
        List<Bun> buns = database.availableBuns();
        assertThat(buns)
                .hasSize(3)
                .extracting(Bun::getName)
                .containsExactly("black bun", "white bun", "red bun");
    }

    @Test
    public void testAvailableIngredientsCount() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertThat(ingredients)
                .hasSize(6);
    }

    @Test
    public void testAvailableIngredientNames() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertThat(ingredients)
                .extracting(Ingredient::getName)
                .contains("hot sauce", "sour cream", "chili sauce", "cutlet", "dinosaur", "sausage");
    }

    @Test
    public void testAvailableIngredientTypes() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertThat(ingredients)
                .extracting(Ingredient::getType)
                .contains(IngredientType.SAUCE, IngredientType.FILLING);
    }
}
