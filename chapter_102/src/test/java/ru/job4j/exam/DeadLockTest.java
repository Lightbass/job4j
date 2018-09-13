package ru.job4j.exam;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * JUnit тест класса DeadLock.
 * @author Alexey Makarov
 * @since 13.09.18
 * @version 0.1
 */
public class DeadLockTest {

    @Test
    public void whenTwoThreadsInDeadLockThenInterrupt() throws InterruptedException {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(stream);
            System.setOut(out);
            DeadLock deadlock = new DeadLock();
            Thread thread1 = new Thread(deadlock);
            Thread thread2 = new Thread(deadlock);
            thread1.start();
            thread2.start();
            thread1.join(100);
            thread2.join(100);
            assertThat(new String(stream.toByteArray()), is(""));
    }
}
