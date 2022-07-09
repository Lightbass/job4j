package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

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

    private static final String FILENAME_HEADER = "filename=";

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
             FileOutputStream fileOutputStream = new FileOutputStream(getFilename(url))) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int totalBytesRead = 0;
            long beginTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                if (totalBytesRead > speed * 1024) {
                    System.out.print("\rDownloading: " + totalBytesRead + " bytes/sec ");
                    totalBytesRead = 0;
                    long sleepTime = 1000 - (System.currentTimeMillis() - beginTime);
                    if (sleepTime > 0) {
                        Thread.sleep(sleepTime);
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
            throw new IllegalArgumentException("Please enter valid arguments: speed(int kb/sec) link(String name) outputFileName(String name)");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args.length > 2 ? args[2] : null;
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();
    }

    private String getFilename(String url) throws MalformedURLException {
        if (fileName != null) {
            return fileName;
        }
        URL urlObject = new URL(url);
        String filename = urlObject.getQuery();
        int filenameIndex = filename == null ? -1 : filename.indexOf(FILENAME_HEADER);
        if (filenameIndex != -1) {
            filename = filename.substring(filenameIndex + FILENAME_HEADER.length());
            if (filename.contains("&")) {
                filename = filename.substring(0, filename.indexOf('&'));
            }
        } else {
            filename = Paths.get(urlObject.getPath()).getFileName().toString();
        }
        return filename;
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
