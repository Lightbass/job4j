package ru.job4j.calculate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса Calculate.
 * @author Alexey Makarov
 * @since 27.07.2018
 * @version 0.1
 */
public class CalculateTest {
	/**
	 * Тест метода echo.
	 */
	@Test
	public void whenTakeNameThenTreeEchoPlusName() {
		String input = "Alexey Makarov";
		String expect = "Echo, echo, echo : Alexey Makarov";
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
	
}
