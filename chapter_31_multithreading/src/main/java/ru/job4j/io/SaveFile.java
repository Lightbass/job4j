package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file);
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(o))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
