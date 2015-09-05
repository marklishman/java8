package com.lishman.java8.lambda;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class LambdaExample {
    
    public static void main(String[] args) {
        new LambdaExample().lambda();
        new LambdaExample().methodReference();
    }
    
    private void lambda() {
        
        // Inner class
        Calculator calc = new Calculator() {
            @Override
            public void calculate(int x, int y) {
                System.out.println("Result: " + (x + y));
            }
        }; 
        calc.calculate(10,  20);
        
        // Anonymous inner class
        new Calculator() {
            @Override
            public void calculate(int x, int y) {
                System.out.println("Result: " + (x * y));
            }
        }.calculate(5,  8);
        
        // Simple lambda expression
        Calculator lambdaCalc = (dividend, divisor) -> System.out.println("Result: " + (dividend / divisor));
        lambdaCalc.calculate(100, 20);

        // Multi-statement lambda expression
        Calculator lambdaMultiCalc = (dividend, divisor) -> {
            if (divisor == 0) {
                throw new RuntimeException("Divide by zero");
            }
            System.out.println("Result: " + (dividend / divisor));
        };
        lambdaMultiCalc.calculate(40, 2);
        
        // Lambda expression returning a value
        Concatenator concatenator = (first, second) -> first + second;
        System.out.println(concatenator.concatenate("abc", "xyz"));
        
        // Built-in interfaces
        Runnable r = () -> System.out.println("Thread: " + Thread.currentThread().getName());
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        
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