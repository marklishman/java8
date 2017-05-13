package com.lishman.java8.lambda;

public class Lambda {
    
    public static void main(String[] args) {

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

}