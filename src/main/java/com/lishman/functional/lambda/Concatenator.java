package com.lishman.functional.lambda;

// A functional interface has a single abstract method

@FunctionalInterface
public interface Concatenator {
    
    // Single Abstract Method (SAM)
    String concatenate(String x, String y);

}
