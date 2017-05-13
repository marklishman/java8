package com.lishman.java8.lambda;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

public class FunctionalInterface {

    public static void main(String[] args) {

        // Function composition
        Function<Integer, Boolean> even = arg -> arg % 2 == 0;
        System.out.println("Function: " + even.apply(6));

        Function<String, String> reverse = StringUtils::reverse;
        Function<String, String> halve = arg -> arg.substring(0, arg.length() / 2);

        System.out.println("Reverse then halve: " + reverse.andThen(halve).apply("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        System.out.println("Reverse then halve: " + halve.compose(reverse).apply ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        System.out.println("Halve then reverse: " + reverse.compose(halve).apply ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        System.out.println("Halve then halve again: " + halve.compose(halve).apply ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));


        IntFunction<String> intFunction = arg -> "The number is " + arg;
        System.out.println("IntFunction: " + intFunction.apply(12321));


        IntToLongFunction square = num -> num * num;
        System.out.println("IntToLongFunction: " + square.applyAsLong(12));

    }

}