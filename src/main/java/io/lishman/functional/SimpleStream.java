package io.lishman.functional;

import java.util.Arrays;
import java.util.List;

public class SimpleStream {

    public static void main(String[] args) {

        List<String> fruits = Arrays.asList("plum", "pear", "apple", "peach", "orange", "pear");

        int length = fruits.stream()
                .filter(fruit -> fruit.startsWith("p"))
                .distinct()
                .mapToInt(fruit -> fruit.length())
                .sum();

        System.out.println("Total length of fruit names beginning with 'p' is " + length);

    }
}