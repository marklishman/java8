package com.lishman.java8;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

/**
 * There is nothing 'magical' about the Function interfaces.
 * In fact they were implemented long before Java 8 by Google Guava.
 */

public class FunctionalInterfaceExample {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        Function<Integer, Boolean> even = arg -> arg % 2 == 0;
        System.out.println("Even: " + even.apply(6));

        IntFunction<String> intFunction = arg -> "The number is " + arg;
        System.out.println("IntFunction: " + intFunction.apply(12321));

        IntToLongFunction square = num -> num * num;
        System.out.println("IntToLongFunction: " + square.applyAsLong(12));

        // Function composition
        Function<String, String> reverse = StringUtils::reverse;
        Function<String, String> halve = arg -> arg.substring(0, arg.length() / 2);

        System.out.println("Reverse then halve: " + reverse.andThen(halve).apply(ALPHABET));
        System.out.println("Reverse then halve: " + halve.compose(reverse).apply (ALPHABET));
        System.out.println("Halve then reverse: " + reverse.compose(halve).apply (ALPHABET));
        System.out.println("Halve then halve again: " + halve.compose(halve).apply (ALPHABET));
    }

}