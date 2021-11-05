package ru.job4j.json;

import org.junit.Test;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * JUnit тест класса JsonServlet.
 * @author Alexey Makarov
 * @since 09.12.18
 * @version 0.1
 */
public class JsonServletTest {

    private ServletInputStream sin = new ServletInputStream() {
        final byte[] bytes = ("{\"name\":\"alex\","
                + "\"sname\":\"mak\", \"pwd\":\"pass\","
                + "\"email\":\"qwe@asd.zxc\","
                + "\"sex\":\"male\","
                + "\"description\":\"I am meeeeeen!\"}").getBytes();
        private int lastIndexRetrieved = -1;
        private ReadListener readListener = null;

        @Override
        public boolean isFinished() {
            return (lastIndexRetrieved == bytes.length - 1);
        }

        @Override
        public boolean isReady() {
            return isFinished();
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            this.readListener = readListener;
            if (!isFinished()) {
                try {
                    readListener.onDataAvailable();
                } catch (IOException e) {
                    readListener.onError(e);
                }
            } else {
                try {
                    readListener.onAllDataRead();
                } catch (IOException e) {
                    readListener.onError(e);
                }
            }
        }

        @Override
        public int read() throws IOException {
            int i;
            if (!isFinished()) {
                i = bytes[lastIndexRetrieved + 1];
                lastIndexRetrieved++;
                if (isFinished() && (readListener != null)) {
                    try {
                        readListener.onAllDataRead();
                    } catch (IOException ex) {
                        readListener.onError(ex);
                        throw ex;
                    }
                }
                return i;
            } else {
                return -1;
            }
        }
    };

    @Test
    public void whenAddOneMoreUserThenFindIt() throws IOException {
        JsonServlet jsonServlet = new JsonServlet();
        JsonService jsonService = JsonService.getInstance();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getInputStream()).thenReturn(sin);
        try {
            jsonServlet.doPost(req, resp);
        } catch (NullPointerException npe) {
            npe.fillInStackTrace();
        }
        User user = jsonService.getUserByEmail("qwe@asd.zxc");
        assertThat(user.getName(), is("alex"));
        assertThat(user.getSname(), is("mak"));
        assertThat(user.getEmail(), is("qwe@asd.zxc"));
        assertThat(user.getPwd(), is("pass"));
        assertThat(user.getSex(), is("male"));
        assertThat(user.getDescription(), is("I am meeeeeen!"));
    }

    @Test
    public void whenGetUsersThenOk() {
        JsonService jsonService = JsonService.getInstance();
        OutputStream os = new ByteArrayOutputStream();
        jsonService.encodeJson(os);
        System.out.println(os.toString());
        assertThat(os.toString().contains("tema666@gmail.com"), is(true));
        assertThat(os.toString().contains("olga222@mail.ru"), is(true));
        assertThat(os.toString().contains("deddok@guddok.ru"), is(true));
    }

}
