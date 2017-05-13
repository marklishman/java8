package com.lishman.java8.lambda;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class MethodReferenceExample {
    
    public static void main(String[] args) {
        new MethodReferenceExample().methodReference();
    }

    private void methodReference() {

        // LambdaExample expression (just calls the method)
        calc((x, y) -> multiply(x, y), 20, 40);
        
        // Method reference
        calc(this::multiply, 20, 40);
        
        // Static method reference
        calc(MethodReferenceExample::add, 20, 40);
        
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