package io.lishman.functional.functionalinterface;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/*
 *      Function<T,R>
 *
 *          IntFunction<R>
 *          LongFunction<R>
 *          DoubleFunction<R>
 *
 *          ToIntFunction<T>
 *          ToLongFunction<T>
 *          ToDoubleFunction<T>
 *
 *          IntToLongFunction
 *          IntToDoubleFunction
 *          LongToIntFunction
 *          LongToDoubleFunction
 *          DoubleToIntFunction
 *          DoubleToLongFunction
 *
 *      UnaryOperator<T>
 *
 *          IntUnaryOperator
 *          LongUnaryOperator
 *          DoubleUnaryOperator
 *
 *      BiFunction<T,U,R>
 *
 *          ToIntBiFunction<T,U>
 *          ToLongBiFunction<T,U>
 *          ToDoubleBiFunction<T,U>
 *
 *      BinaryOperator<T>
 *          IntBinaryOperator
 *          LongBinaryOperator
 *          DoubleBinaryOperator
 *
 *
 *
 *      Supplier<T>
 *
 *          IntSupplier
 *          LongSupplier
 *          DoubleSupplier
 *
 *          BooleanSupplier
 *
 *
 *
 *      Consumer<T>
 *
 *          IntConsumer
 *          LongConsumer
 *          DoubleConsumer
 *
 *          ObjIntConsumer<T>
 *          ObjLongConsumer<T>
 *          ObjDoubleConsumer<T>
 *
 *      BiConsumer<T,U>
 *
 *
 *
 *      Predicate<T>
 *
 *          IntPredicate
 *          LongPredicate
 *          DoublePredicate
 *
 *      BiPredicate<T,U>
 */

public class BuiltInFunctionalInterfaces {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        //---------- Function<T,R>

        Function<String, String> reverseFunction = StringUtils::reverse;
        Function<String, String> halveFunction = arg -> arg.substring(0, arg.length() / 2);

        System.out.println("Reverse then halve: " + reverseFunction.andThen(halveFunction).apply(ALPHABET));
        System.out.println("Reverse then halve: " + halveFunction.compose(reverseFunction).apply (ALPHABET));
        System.out.println("Halve then reverse: " + reverseFunction.compose(halveFunction).apply (ALPHABET));
        System.out.println("Halve then halve again: " + halveFunction.compose(halveFunction).apply (ALPHABET));

        // Identity (use as a default - no action)
        String withUpperCase = Stream.of("one two three")
                .flatMap(value -> splitter(value, text -> text.toUpperCase()))
                .collect(Collectors.joining(" "));

        System.out.println("Upper case: " + withUpperCase);

        String withIdentity = Stream.of("one two three")
                .flatMap(value -> splitter(value, UnaryOperator.identity()))
                .collect(Collectors.joining(" "));

        System.out.println("No action" + withIdentity);


        //---------- [Type]Function<R>

        LongFunction<BigDecimal> longFunction = number -> BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
        System.out.println("Square: " + longFunction.apply(Long.MAX_VALUE));

        //---------- To[Type]Function<T>

        ToDoubleFunction<String> toDoubleFunction = value -> Double.parseDouble(value) / 2;
        System.out.println("Half: " + toDoubleFunction.applyAsDouble("333"));

        //---------- [Type]To[Type]Function

        LongToDoubleFunction longToDoubleFunction = number -> (double) number / 4;
        System.out.println("Quarter: " + longToDoubleFunction.applyAsDouble(55));


        //---------- UnaryOperator<T>

        UnaryOperator<int[]> unaryOperator = array -> Arrays.copyOfRange(array, 0, array.length / 2);
        int[] half = unaryOperator.apply(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println("Half of array: " + Arrays.toString(half));

        UnaryOperator<String> reverseUnaryOperator = StringUtils::reverse;
        System.out.println("Reverse: " + reverseUnaryOperator.apply(ALPHABET));

        //---------- [Type]UnaryOperator

        DoubleUnaryOperator doubleUnaryOperator = d -> d * d * d;
        System.out.println("Power of 3: " + doubleUnaryOperator.applyAsDouble(4.4));


        //---------- BiFunction<T,U,R>

        BiFunction<int[], Integer, String> biFunction = (array, item) ->
                String.format("%s was found at position %s", item, Arrays.binarySearch(array, item));
        System.out.println(biFunction.apply(new int[]{10,20,30,40,50}, 30));


        //---------- ToIntBiFunction<T,U>

        ToIntBiFunction<String, Character> toIntBiFunction = (s, c) -> s.length() - s.replace(c.toString(), "").length();
        System.out.println("Character count: " + toIntBiFunction.applyAsInt("Beware the moon", 'e'));

        //---------- BinaryOperator<T>

        BinaryOperator<String> binaryOperator = (x, y)  -> x.toUpperCase() + "_" + y.toUpperCase();
        System.out.println("Uppercase concat: " + binaryOperator.apply("one", "two"));

        //---------- [Type]BinaryOperator

        LongBinaryOperator longBinaryOperator = (x, y) -> Math.max(x, y);
        System.out.println("Max is " + longBinaryOperator.applyAsLong(20, 10));


        //---------- Supplier<T>

        Supplier<String> supplier = () -> "abc";
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

        //---------- [Type]Supplier

        BooleanSupplier booleanSupplier = () -> supplier.get().startsWith("a");
        System.out.println("Boolean Supplier: " + booleanSupplier.getAsBoolean());


        //---------- Consumer<T>

        List<Integer> list = new ArrayList<>();
        Consumer<Integer> consumer = number -> {
            list.add(number);
        };
        consumer.accept(123);
        consumer.accept(456);
        consumer.andThen(number -> System.out.println("List: " + list)).accept(789);

        //---------- [Type]Consumer<T>

        IntConsumer intConsumer = number -> list.add(number);
        intConsumer.accept(789);

        //---------- Obj[Type]Consumer<T>

        ObjLongConsumer<AtomicLong> objLongConsumer = (al, l) -> al.addAndGet(l);
        AtomicLong atomicLong = new AtomicLong();
        LongStream.rangeClosed(20, 50).forEach(
                l -> objLongConsumer.accept(atomicLong, l)
        );
        System.out.println("Accumulator: " + atomicLong.get());


        //---------- Predicate<T>

        Predicate<Integer> negativeCheck = value -> value < 0;
        System.out.println("Predicate: " + negativeCheck.test(-123));
        System.out.println("Negated Predicate: " + negativeCheck.negate().test(-123));
        System.out.println("And Predicate: " + negativeCheck.and(integer -> integer > -100).test(-123));
        System.out.println("Or Predicate: " + negativeCheck.or(integer -> integer > 100).test(123));
        System.out.println("isEqual: " + Predicate.isEqual("abc").test("ab" + "c"));

        //---------- [Type]Predicate

        IntPredicate intPredicate = i -> i < 0;
        System.out.println("Negative: " + intPredicate.test(-123));


        //---------- BiPredicate<T,U>

        BiPredicate<String[], String> biPredicate = (array, s) -> Arrays.binarySearch(array, s) > -1;
        System.out.println("String in Array? " + biPredicate.test(new String[]{"a","b","c"}, "b"));

    }

    private static Stream<String> splitter(String s, UnaryOperator<String> function) {
        return Arrays.stream(s.split(" "))
                .map(function);
    }

}
