package ru.job4j.io.filter;

import java.util.function.Predicate;

public class ParseFilter {
    public static final Predicate<Character> RAW_FILTER = character -> character < 0x80;
    public static final Predicate<Character> WITHOUT_UNICODE_FILTER = character -> true;
}
