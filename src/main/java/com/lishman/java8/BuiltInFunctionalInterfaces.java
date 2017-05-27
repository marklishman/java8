package com.lishman.java8;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.IntStream;

/*
 *      Function<T,R>
 *
 *          IntFunction<R>
 *          LongFunction<R>
 *          DoubleFunction<R>
 *
 *          IntToLongFunction
 *          IntToDoubleFunction
 *          LongToIntFunction
 *          LongToDoubleFunction
 *          DoubleToIntFunction
 *          DoubleToLongFunction
 *
 *
 *      BiFunction<T,U,R>
 *
 *          ToIntBiFunction<T,U>
 *          ToLongBiFunction<T,U>
 *          ToDoubleBiFunction<T,U>
 *
 *
 *      Supplier<T>
 *
 *          IntSupplier
 *          LongSupplier
 *          DoubleSupplier
 *          BooleanSupplier
 *
 *
 *      Consumer<T>
 *
 *          IntConsumer
 *          LongConsumer
 *          DoubleConsumer
 *          ObjIntConsumer<T>
 *          ObjLongConsumer<T>
 *          ObjDoubleConsumer<T>
 *
 *
 *      Predicate
 */

public class BuiltInFunctionalInterfaces {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        //---------- Function

        Function<String, String> reverseFunction = StringUtils::reverse;
        Function<String, String> halve = arg -> arg.substring(0, arg.length() / 2);

        System.out.println("Reverse then halve: " + reverseFunction.andThen(halve).apply(ALPHABET));
        System.out.println("Reverse then halve: " + halve.compose(reverseFunction).apply (ALPHABET));
        System.out.println("Halve then reverse: " + reverseFunction.compose(halve).apply (ALPHABET));
        System.out.println("Halve then halve again: " + halve.compose(halve).apply (ALPHABET));

        // Not sure what use this is
        System.out.println(Function.identity().apply("yes"));


        IntFunction square = number -> number * number;
        System.out.println("Square: " + square.apply(33));


        LongToDoubleFunction half = number -> (double) number / 2;
        System.out.println("Half: " + half.applyAsDouble(33));


        //---------- Consumer

        List<Integer> list = new ArrayList<>();
        Consumer<Integer> consumer = number -> {
            list.add(number);
            System.out.printf("Added %s, Size: %s%n", number, list.size());
        };
        consumer.accept(123);
        consumer.accept(456);

        IntConsumer intConsumer = number -> list.add(number);
        intConsumer.accept(789);


        //---------- Supplier

        Supplier<Integer> supplier = () -> 123;
        System.out.println("Supplied " + supplier.get());
        System.out.println("..and " + supplier.get());

        // Supplier can be used to re-use the a stream
        IntStream intStream = IntStream.range(10, 24);
        Supplier<IntStream> sup = () -> IntStream.range(20, 27);

        System.out.println("inStream Count: " + intStream.count());
        try {
            System.out.println("intStream Count: " + intStream.count());
        } catch (IllegalStateException e) {
            // throws java.lang.IllegalStateException: stream has already been operated upon or closed
        }

        System.out.println("Supplier Count: " + sup.get().count());
        System.out.println("Supplier Count: " + sup.get().count());

        LongSupplier longSupplier = () -> Long.MAX_VALUE;
        System.out.println("Long Supplier: " + longSupplier.getAsLong());


        //---------- Predicate

        Predicate<Integer> negativeCheck = value -> value < 0;
        System.out.println("Predicate: " + negativeCheck.test(-123));
        System.out.println("Negated Predicate: " + negativeCheck.negate().test(-123));
        System.out.println("And Predicate: " + negativeCheck.and(integer -> integer > -100).test(-123));
        System.out.println("Or Predicate: " + negativeCheck.or(integer -> integer > 100).test(123));


        //---------- UnaryFunction

        UnaryOperator<String> reverseUnaryOperator = StringUtils::reverse;
        System.out.println("Reverse: " + reverseUnaryOperator.apply(ALPHABET));


    }

}
