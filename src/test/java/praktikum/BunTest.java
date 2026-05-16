package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static praktikum.Constants.PRICE_DELTA;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тест {index}: булочка {0}, цена {1}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Обычная булочка", 100f},
                {"Булочка с кунжутом", 150f},
                {"Неправильная булочка", -100f},
                {"", 0f},
                {null, Float.MAX_VALUE}, // работа метода при заполнении всех ячеек памяти
        };
    }

    @Test
    public void testGetName() {
        Bun bun = new Bun(name, price);
        assertThat(bun.getName())
                .as("Неверное имя булочки")
                .isEqualTo(name);
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun(name, price);
        assertThat(bun.getPrice())
                .as("Неверная цена булочки")
                .isCloseTo(price, within(PRICE_DELTA)); // установлена точность для проверки без искажений для валютных операций
    }
}
