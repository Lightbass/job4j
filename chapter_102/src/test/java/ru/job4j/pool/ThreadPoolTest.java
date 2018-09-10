package ru.job4j.pool;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


import java.util.Arrays;

/**
 * JUnit тест класса ThreadPool.
 * @author Alexey Makarov
 * @since 10.09.18
 * @version 0.1
 */
public class ThreadPoolTest {

    ThreadPool pool;

    @Before
    public void prepare() {
        pool = new ThreadPool();
    }

    @Test
    public void whenTaskWorkOnTasksThenOK() throws InterruptedException {
        final int[] num = new int[5];
        Arrays.fill(num, 1);
        Runnable[] runTask = new Runnable[5];
        for (int i = 0; i != runTask.length; i++) {
            final int index = i;
            final int fact = (i + 1) * 2;
            runTask[i] = () -> {
                for (int n = 2; n != fact + 1; n++) {
                    num[index] = num[index] * n;
                }
            };

            pool.work(runTask[i]);
        }
        Thread.sleep(500);
        pool.shutdown();
        assertThat(num[0] == 2 && num[1] == 24
                && num[2] == 720 && num[3] == 40320
                && num[4] == 3628800, is(true));
    }
}
