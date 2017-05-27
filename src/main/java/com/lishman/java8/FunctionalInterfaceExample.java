package com.lishman.java8;

import org.apache.commons.lang3.StringUtils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntToLongFunction;
import java.util.function.Predicate;

/**
 * There is nothing 'magical' about the Function interfaces.
 * In fact they were implemented long before Java 8 by Google Guava.
 */

public class FunctionalInterfaceExample {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        // They can be implemented just like any other interface
        Function<Integer, Boolean> odd = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer value) {
                return value % 2 != 0;
            }
        };
        System.out.println("Odd: " + odd.apply(5));

        // Lambdas, however, are magical! Check out this implementation compared to the one above.
        Function<Integer, Boolean> even = arg -> arg % 2 == 0;
        System.out.println("Even: " + even.apply(6));

        // Lambdas are simply a concise way to define an anonymous function
        IntToLongFunction square = num -> num * num;
        System.out.println("Square: " + square.applyAsLong(12));

        BiFunction<String, Predicate<Integer>, Boolean> wordCheck = (word, pred) -> pred.test(word.split(" ").length);
        System.out.println("Empty: " + wordCheck.apply("one", wordCount -> wordCount == 0));
        System.out.println("Three words: " + wordCheck.apply("one two three", wc -> wc == 3));


        // Function composition
        Function<String, String> reverse = StringUtils::reverse;
        Function<String, String> halve = arg -> arg.substring(0, arg.length() / 2);

        System.out.println("Reverse then halve: " + reverse.andThen(halve).apply(ALPHABET));
        System.out.println("Reverse then halve: " + halve.compose(reverse).apply (ALPHABET));
        System.out.println("Halve then reverse: " + reverse.compose(halve).apply (ALPHABET));
        System.out.println("Halve then halve again: " + halve.compose(halve).apply (ALPHABET));
    }

}