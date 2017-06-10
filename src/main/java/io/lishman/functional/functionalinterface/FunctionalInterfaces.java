package io.lishman.functional.functionalinterface;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.*;

public class FunctionalInterfaces {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {


        //~~~~ Function<T,R>

        Function<String, Integer> textSize = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        System.out.println("Text size is " + textSize.apply("one two three"));

        LongFunction<BigDecimal> square = new LongFunction<BigDecimal>() {
            @Override
            public BigDecimal apply(long value) {
                return BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(value));
            }
        };
        System.out.println("Square of 12 is " + square.apply(12));


        //~~~~ BiFunction<T,U,R>

        BiFunction<int[], Integer, String> position = new BiFunction<int[], Integer, String>() {
            @Override
            public String apply(int[] array, Integer item) {
                return String.format("%s found at %s", item, Arrays.binarySearch(array, item));
            }
        };
        System.out.println(position.apply(new int[]{10,20,30,40,50}, 30));


        //~~~~ ToIntBiFunction<T,U>

        ToIntBiFunction<String, Character> charCount = new ToIntBiFunction<String, Character>() {
            @Override
            public int applyAsInt(String s, Character c) {
                return s.length() - s.replace(c.toString(), "").length();
            }
        };
        System.out.println("'e' count " + charCount.applyAsInt("one two three",'e'));


        //~~~~ Predicate<T>

        Predicate<Integer> negativeCheck = new Predicate<Integer>() {
            @Override
            public boolean test(Integer value) {
                return value < 0;
            }
        };
        System.out.println("Number is negative? " + negativeCheck.test(-123));


        //~~~~ BiPredicate<T,U>

        BiPredicate<String[], String> findString = new BiPredicate<String[], String>() {
            @Override
            public boolean test(String[] array, String s) {
                return Arrays.binarySearch(array, s) > -1;
            }
        };
        System.out.println("String is in array? " +
                findString.test(new String[]{"a","b","c"}, "b"));

    }
}
