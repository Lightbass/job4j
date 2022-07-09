package ru.job4j.io;

import ru.job4j.io.filter.RawFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.function.Predicate;

public class ParseFile {

    private final File file;
    private final Predicate<Character> filter;

    public ParseFile(File file, Predicate<Character> filter) {
        this.file = file;
        this.filter = filter;
    }

    public ParseFile(File file) {
        this(file, new RawFilter());
    }

    private synchronized String getContent() {
        String output = "";
        int data;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while ((data = in.read()) > 0) {
                char charData = (char) data;
                if (filter.test(charData)) {
                    output += charData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
