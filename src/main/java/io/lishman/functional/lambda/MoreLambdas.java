package io.lishman.functional.lambda;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class MoreLambdas {

    Function<String, Integer> function = new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return s.length();
        }
    };

    Function<String, Integer> lambda1 = (String s) -> {
        return s.length();
    };

    Function<String, Integer> lambda2 = s -> {
        return s.length();
    };

    Function<String, Integer> lambda3 = s -> s.length();

    Function<String, Integer> lambda4 = String::length;

    ToIntFunction<String> lambda5 = String::length;


    // another example

    @FunctionalInterface
    interface Calculator {
        // Single Abstract Method (SAM)
        int calculate(int x, int y);
    }

    public static void main(String[] args) {

        MoreLambdas.Calculator sum = new MoreLambdas.Calculator() {
            @Override
            public int calculate(int x, int y) {
                return x + y;
            }
        };

        // multiple arguments must be wrapped in parentheses
        MoreLambdas.Calculator lambdaSum = (a, b) -> a + b;

        System.out.println("Sum: " + lambdaSum.calculate(10, 20));


        int product = new MoreLambdas.Calculator() {
            @Override
            public int calculate(int x, int y) {
                return x * y;
            }
        }.calculate(10, 20);

        MoreLambdas.Calculator lambdaProduct = (a, b) -> a * b;

        System.out.println("Product: " + lambdaProduct.calculate(10, 20));


        // we can pass the functional interface as a parameter
        logger(120, 200, lambdaProduct);
    }

    private static void logger(final int x, final int y, final MoreLambdas.Calculator calculator) {
        System.out.printf("Values: %s and %s, Result: %s%n", x, y, calculator.calculate(x, y));
    }

}
