package com.lishman.java8.lambda;

import java.util.function.Predicate;

public class PredicateExample {

    public static void main(String[] args) {

        final Predicate<Integer> even = arg -> arg % 2 == 0;
        final Predicate<Integer> positive = arg -> arg >= 0;

        displayResult("", even, (-8));
        displayResult("(and): ", even.and(positive), 8);
        displayResult("(or): ", even.or(positive), -8);
        displayResult("(negate): ", even.negate(), 3);
        displayResult("(complex): ", even.negate().and(positive), 3);

    }

    private static void displayResult(final String message, final Predicate<Integer> pred, final int value) {
        System.out.printf("Predicate %s: %s%n", message, pred.test(value));
    }

}