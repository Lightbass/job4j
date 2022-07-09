package ru.job4j.io.filter;

import java.util.function.Predicate;

public class RawFilter implements Predicate<Character> {
    @Override
    public boolean test(Character character) {
        return character < 0x80;
    }
}
