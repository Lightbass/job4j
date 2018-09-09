package ru.job4j.cache;

import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса BaseCache.
 * @author Alexey Makarov
 * @since 08.09.18
 * @version 0.1
 */
public class BaseCacheTest {
    private BaseCache cache;
    private Base base1;
    private Base base2;

    @Before
    public void prepare() {
        cache = new BaseCache();
        base1 = new Base(1, "One");
        base2 = new Base(2, "Two");
        cache.add(base1);
        cache.add(base2);
    }

    /**
     * Тестовый метод для проверки выброса исключения.
     * Задержка в 200 и 400 миллисекунд для того, чтобы обе нити имели одну версию модели, а первой изменила её именно
     * первая нить, чтобы тест выдавал одинаковый результат.
     * @throws InterruptedException
     */
    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    Base base = cache.get(1);
                    base.setName("11");

                    try {
                        Thread.sleep(200);
                        cache.update(base);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    Base base = cache.get(1);
                    base.setName("111");
                    try {
                        Thread.sleep(400);
                        cache.update(base);
                    } catch (OptimisticException | InterruptedException oe) {
                        ex.set(oe);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(ex.get().getMessage(), is("This model has been updated."));
    }

    @Test
    public void whenEditModelThenOK() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    Base base = cache.get(1);
                    base.setName("11");
                    cache.update(base);
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    Base base = cache.get(1);
                    base.setName("111");
                    cache.update(base);
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(cache.get(1).getVersion() == 2
                && cache.get(1).getName().equals("111"), is(true));
    }

    @Test
    public void whenDeleteModelThenOK() {
        cache.delete(base1);
        assertThat(cache.get(base1.getId()), nullValue());
    }
}
