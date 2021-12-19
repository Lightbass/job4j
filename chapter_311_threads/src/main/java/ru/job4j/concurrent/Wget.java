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
    private final String fileName;

    /**
     * Инициализация объекта класса.
     * @param url ссылка по которой будет скачан файл.
     * @param speed скорость в килобайтах в секунду.
     */
    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
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
                    long sleepTime = 1000 - (System.currentTimeMillis() - beginTime);
                    if (sleepTime > 0) {
                        Thread.sleep(1000 - (System.currentTimeMillis() - beginTime));
                    }
                    beginTime = System.currentTimeMillis();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (!validate(args)) {
            System.out.println("Please enter valid arguments: speed(int) link(String) outputFileName(String)");
            return;
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args.length > 2 ? args[2] : "downloadedFile";
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();
    }

    private static boolean validate(String[] args) {
        boolean result = true;
        String urlValidate = "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&//=]*)";
        if (args.length < 2 || !args[0].matches(urlValidate) || !args[1].matches("^\\d+$")) {
            result = false;
        }
        return result;
    }
}
