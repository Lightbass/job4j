package ru.job4j.exam;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса StringBuffer.
 * @author Alexey Makarov
 * @since 17.09.18
 * @version 0.1
 */
public class StringBufferTest {

    @Test
    public void whenPut10ThreeTimesThen101010() {
        StringBuffer sbuf = new StringBuffer();
        sbuf.putToBuffer(10);
        sbuf.putToBuffer(10);
        sbuf.putToBuffer(10);
        assertThat(sbuf.toString(), is("101010"));
    }
}
