package ru.job4j.io.filter;

import java.util.function.Predicate;

public class WithoutUnicodeFilter implements Predicate<Character> {
    @Override
    public boolean test(Character character) {
        return true;
    }
}
