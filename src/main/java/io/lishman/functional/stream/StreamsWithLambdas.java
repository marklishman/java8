package io.lishman.functional.stream;

import java.util.Arrays;
import java.util.List;


public class StreamsWithLambdas {

    public static void main(String[] args) {

        final List<Integer> numbers = Arrays.asList(1, -6, 10, 4, 5, -34, 44, 6, 99, -32, -8, 51);

        final int sum = numbers
                .stream()
                .mapToInt(Math::abs)
                .map(n -> n * 2)
                .filter(n -> n > 10)
                .sum();

        System.out.println("The sum is " + sum);


        final List<String> strings = Arrays.asList("one", "two", "three", "four", "five");

        strings.stream()
                .filter(s -> s.length() > 3)
                .map(String::toUpperCase)
                .forEach(s -> System.out.println(s));

    }
}