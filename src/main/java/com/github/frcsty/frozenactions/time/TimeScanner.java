package com.github.frcsty.frozenactions.time;

import java.util.Arrays;
import java.util.function.Predicate;

final class TimeScanner {

    private final char[] time;
    private int index = 0;

    TimeScanner(final String time) {
        this.time = time.toCharArray();
    }

    boolean hasNext() {
        return index < time.length - 1;
    }

    long nextLong() {
        return Long.valueOf(String.valueOf(next(Character::isDigit)));
    }

    String nextString() {
        return String.copyValueOf(next(codePoint ->
                Character.isAlphabetic(Integer.valueOf(codePoint))
        ));
    }

    private char[] next(final Predicate<Character> condition) {
        final int startIndex = index;
        while (++index < time.length && condition.test(time[index])) ;
        return Arrays.copyOfRange(time, startIndex, index);
    }

}
