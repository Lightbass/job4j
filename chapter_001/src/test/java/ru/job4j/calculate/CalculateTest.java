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
	public void whenTakeNameThenThreeEchoPlusName() {
		String input = "Alexey Makarov";
		String expect = "Echo, echo, echo : Alexey Makarov";
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
	
	/**
	 * Тест метода суммирования add().
	 */
	@Test
	public void whenThreeAddToThreeThenSix() {
		Double x = 3D, y = 3D;
		Double expect = 6.0;
		Calculate calc = new Calculate();
		Double result = calc.add(x, y);
		assertThat(result, is(expect));
	}
	
	/**
	 * Тест метода вычитания sub().
	 */
	@Test
	public void whenThreeSubToThreeThenZero() {
		Double x = 3D, y = 3D;
		Double expect = 0.0;
		Calculate calc = new Calculate();
		Double result = calc.sub(x, y);
		assertThat(result, is(expect));
	}
	
	/**
	 * Тест метода умножения mul().
	 */
	@Test
	public void whenThreeMulToThreeThenNine() {
		Double x = 3D, y = 3D;
		Double expect = 9.0;
		Calculate calc = new Calculate();
		Double result = calc.mul(x, y);
		assertThat(result, is(expect));
	}
	
	/**
	 * Тест метода деления div().
	 */
	@Test
	public void whenThreeDivToThreeThenOne() {
		Double x = 3D, y = 3D;
		Double expect = 1.0;
		Calculate calc = new Calculate();
		Double result = calc.div(x, y);
		assertThat(result, is(expect));
	}
	}
