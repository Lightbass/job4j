package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Класс для скачивания файла с ограниченной скоростью.
 *
 * @author Alexey Makarov
 * @version 0.1
 */
public class Wget implements Runnable {
    private final String url;
    private final int speed;

    /**
     * Инициализация объекта класса.
     * @param url ссылка по которой будет скачан файл.
     * @param speed скорость в килобайтах в секунду.
     */
    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int totalBytesRead = 0;
            long beginTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                totalBytesRead += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (totalBytesRead > speed * 1024) {
                    System.out.print("\rDownloading: " + totalBytesRead + " bytes/sec ");
                    totalBytesRead = 0;
                    Thread.sleep(1000 - (System.currentTimeMillis() - beginTime));
                    beginTime = System.currentTimeMillis();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
