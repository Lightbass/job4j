package lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Calculator {

    public void multiply(int start, int finish, int value,
                         BiFunction<Integer, Integer, Integer> operation,
                         Consumer<Integer> action) {
        IntStream.range(start, finish)
                .map(number -> operation.apply(number, value))
                .forEach(action::accept);
    }
}
