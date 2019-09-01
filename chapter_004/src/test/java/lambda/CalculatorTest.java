package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса Calculator.
 *
 * @author Alexey Makarov
 * @version 0.1
 * @since 0.1
 */
public class CalculatorTest {

    @Test
    public void whenMultiplyTableOn9ThenOk() {
        Calculator calculator = new Calculator();
        List<Integer> list = new ArrayList<>();
        calculator.multiply(1, 10, 9, (a, b) -> a * b, list::add);
        assertThat(list, is(Arrays.asList(9, 18, 27, 36, 45, 54, 63, 72, 81)));
    }
}
