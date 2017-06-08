package io.lishman.functional;

import java.util.Arrays;
import java.util.List;

public class SimpleStream {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("apple", "pear", "orange", "peach", "pear", "pineapple");

        int length = strings.stream()
                .filter(fruit -> fruit.startsWith("p"))
                .distinct()
                .mapToInt(fruit -> fruit.length())
                .sum();

        System.out.println("Total length of fruit names beginning with 'p' is " + length);

    }
}