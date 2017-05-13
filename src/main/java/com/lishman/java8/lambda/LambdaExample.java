package com.lishman.java8.lambda;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

public class LambdaExample {
    
    public static void main(String[] args) {
//        new LambdaExample().lambda();
//        new LambdaExample().functionalInterfaces();
        new LambdaExample().iterables();
//        new LambdaExample().methodReference();
    }
    
    private void lambda() {

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
        System.out.println("Lambda expression: " + lambdaCalc.calculate(100, 20));


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
    }

    private void functionalInterfaces() {

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

    private void iterables() {

        Arrays.asList(1,2,3,4,5).forEach(number -> System.out.println(number));

    }


    private void methodReference() {
        
        // Lambda expression (just calls the method)
        calc((x, y) -> multiply(x, y), 20, 40);
        
        // Method reference
        calc(this::multiply, 20, 40);
        
        // Static method reference
        calc(LambdaExample::add, 20, 40);
        
        // Built in method reference
        stringFunction(String::toLowerCase, "A STRING");
    }
    
    private void calc(IntBinaryOperator ibo, int x, int y) {
        System.out.println("Result: " + ibo.applyAsInt(x, y));
    }
    
    private int multiply (int first, int second) {
        return first * second;
    }

    private static int add(int first, int second) {
        return first + second;
    }
    
    public void stringFunction(Function<String, String> stringOperator, String s) {
        System.out.println(stringOperator.apply(s));
    }
    
}