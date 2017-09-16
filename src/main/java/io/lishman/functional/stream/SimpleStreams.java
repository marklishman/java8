package io.lishman.functional.stream;

import java.util.Arrays;
import java.util.List;


public class SimpleStreams {

    public static void main(String[] args) {

        final List<Integer> numbers = Arrays.asList(1, -6, 10, 4, 5, -34, 44, 6, 99, -32, -8, 51);

        final int sum = numbers
                .stream()
                .mapToInt(Math::abs)
                .filter(n -> n > 10)
                .skip(2)
                .limit(4)
                .sum();

        System.out.println("The sum is " + sum);
    }
}