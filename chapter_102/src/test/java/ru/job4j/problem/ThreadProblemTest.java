package ru.job4j.problem;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса ThreadProblem.
 * @author Alexey Makarov
 * @since 04.09.18
 * @version 0.1
 */
public class ThreadProblemTest {

    /**
     * Тест отражает проблемы с многопоточностью, но метод assertThat я убрал, ибо при тесте в travis результат таки
     * временами сходится, я бы мог поставить число побольше, да тест начинает выполняться дольше, поэтому просто убрал.
     */
    @Test
    public void when2ThreadsIncrementSameValueThenFailed() {
        Increment inc = new Increment();
        ThreadProblem problem = new ThreadProblem(100000, inc);
        Thread thread1 = new Thread(problem);
        Thread thread2 = new Thread(problem);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println(inc.get());
        //assertThat(inc.get() == 200000, is(false));
    }
}
