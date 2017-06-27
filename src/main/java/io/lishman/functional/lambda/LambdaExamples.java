package io.lishman.functional.lambda;

import org.apache.commons.lang3.StringUtils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntToLongFunction;
import java.util.function.Predicate;

public class LambdaExamples {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @FunctionalInterface
    public interface Calculator {

        // Single Abstract Method (SAM)
        int calculate(int x, int y);

    }

    @FunctionalInterface
    public interface Concatenator {

        // Single Abstract Method (SAM)
        String concatenate(String x, String y);

    }

    public static void main(String[] args) {

        final Predicate<Integer> even = arg -> arg % 2 == 0;
        final Predicate<Integer> positive = arg -> arg >= 0;

        displayResult("", even, (-8));
        displayResult("(and): ", even.and(positive), 8);
        displayResult("(or): ", even.or(positive), -8);
        displayResult("(negate): ", even.negate(), 3);
        displayResult("(complex): ", even.negate().and(positive), 3);



        // Inner class
        Calculator calc = new Calculator() {
            @Override
            public int calculate(int x, int y) {
                return x + y;
            }
        };
        System.out.println("Inner class: " + calc.calculate(10, 20));


        // Anonymous inner class
        new Calculator() {
            @Override
            public int calculate(int x, int y) {
                return x * y;
            }
        }.calculate(5, 8);
        System.out.println("Anonymous inner class: " + calc.calculate(10, 20));


        // Simple lambda expression
        Calculator lambdaCalc = (dividend, divisor) -> dividend / divisor;
        System.out.println("LambdaExample expression: " + lambdaCalc.calculate(100, 20));


        // Multi-statement lambda expression
        Calculator lambdaMultiCalc = (dividend, divisor) -> {
            if (divisor == 0) {
                throw new RuntimeException("Divide by zero");
            }
            return dividend / divisor;
        };
        System.out.println("Multi-statement lambda expression: " + lambdaMultiCalc.calculate(40, 2));


        // Another lambda expression returning a value
        Concatenator concatenator = (first, second) -> first + second;
        System.out.println(concatenator.concatenate("abc", "xyz"));
        System.out.println("Another lambda expression: " + lambdaCalc.calculate(100, 20));


        // Built-in interfaces
        Runnable r = () -> System.out.println("Thread: " + Thread.currentThread().getName());
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();

        // They can be implemented just like any other interface
        Function<Integer, Boolean> odd = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer value) {
                return value % 2 != 0;
            }
        };
        System.out.println("Odd: " + odd.apply(5));

        // Lambdas, however, are magical! Check out this implementation compared to the one above.
        Function<Integer, Boolean> evenNumber = arg -> arg % 2 == 0;
        System.out.println("Even: " + evenNumber.apply(6));

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

    private static void displayResult(final String message, final Predicate<Integer> pred, final int value) {
        System.out.printf("Predicate %s: %s%n", message, pred.test(value));
    }


}
