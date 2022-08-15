package ru.job4j.io;

import ru.job4j.io.filter.ParseFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.function.Predicate;

public class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContentRaw() {
        return getContent(ParseFilter.RAW_FILTER);
    }

    public synchronized String getContentWithoutUnicode() {
        return getContent(ParseFilter.WITHOUT_UNICODE_FILTER);
    }

    public synchronized String getContent(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        int data;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while ((data = in.read()) != -1) {
                char charData = (char) data;
                if (filter.test(charData)) {
                    output.append(charData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
