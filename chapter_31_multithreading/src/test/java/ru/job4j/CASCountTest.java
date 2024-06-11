package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {

    @Test
    void whenSingleThreadRunThenOk() {
        CASCount casCount = new CASCount();
        for (int i = 0; i < 500; i++) {
            casCount.increment();
        }
        assertThat(casCount.get()).isEqualTo(500);
    }

    @Test
    void whenMultiThreadingRunThenOk() throws InterruptedException {
        int threads = 5;
        CASCount casCount = new CASCount();
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                casCount.increment();
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        while (threads > 0) {
            executor.execute(task);
            threads--;
        }
        executor.awaitTermination(1, TimeUnit.SECONDS);
        assertThat(casCount.get()).isEqualTo(500);
    }
}
