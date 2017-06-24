package io.lishman.functional.functionalinterface;

import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.Predicate;

public class BuiltInExamples {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        //~~~~ Function<T,R>

        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        System.out.println("Text size is " + function.apply(ALPHABET));


        //~~~~ [Type]To[Type]Function

        LongToDoubleFunction longToDoubleFunction = new LongToDoubleFunction() {
            @Override
            public double applyAsDouble(long number) {
                return (double) number / 4;
            }
        };
        System.out.println("Quarter: " + longToDoubleFunction.applyAsDouble(55));


        //~~~~ Predicate<T>

        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String value) {
                return value.toUpperCase().equals(value);
            }
        };
        System.out.println("Is upper case? " + predicate.test(ALPHABET));


        //~~~~ [Type]Predicate

        IntPredicate intPredicate = new IntPredicate() {
            @Override
            public boolean test(int i) {
                return i < 0;
            }
        };
        System.out.println("Is negative? " + intPredicate.test(-123));
    }
}
