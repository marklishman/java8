package com.lishman.java8.lambda;

// A functional interface has a single abstract method
// eg Runnable, Comparator

@FunctionalInterface
public interface Calculator {
    
    // Single Abstract Method (SAM)
    int calculate(int x, int y);

}
