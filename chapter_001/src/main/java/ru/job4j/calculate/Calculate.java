package ru.job4j.calculate;

/**
 * Class Класс для вычисления арифметических операций + - * / ^.
 * @author Alexey Makarov
 * @since 27.07.2018
 * @version 0.1
 */

public class Calculate{
	
	/**
	 * Главный метод для запуска программы.
	 * @param args Параметры запуска.
	 */
	public static void main(String[] args) {
		Calculate calc = new Calculate();
		
		System.out.println("Hello world");		
		System.out.println("Calculate: 3 + 3 = ");
		System.out.println(calc.add(3,3));
		System.out.println("Calculate: a + b = ");
		System.out.println(calc.sub(3,3));
		System.out.println("Calculate: a + b = ");
		System.out.println(calc.mul(3,3));
		System.out.println("Calculate: a + b = ");
		System.out.println(calc.div(3,3));
	}
	
	/**
	 * Сложение.
	 * @param first первый аргумент.
	 * @param second второй аргумент.
	 * @return результат сложения.
	 */
	public static double add(double first, double second){
		return first + second;
	}
	
	/**
	 * Вычитание.
	 * @param first первый аргумент.
	 * @param second второй аргумент.
	 * @return результат вычитания.
	 */
	public static double sub(double first, double second){
		return first - second;
	}
	
	/**
	 * Умножение.
	 * @param first первый аргумент.
	 * @param second второй аргумент.
	 * @return результат Умножения.
	 */
	public static double mul(double first, double second){
		return first * second;
	}
	
	/**
	 * Деление.
	 * @param first первый аргумент.
	 * @param second второй аргумент.
	 * @return результат деления.
	 */
	public static double div(double first, double second){
		return first / second;
	}
}