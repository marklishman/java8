package com.lishman.java8.lambda;

// A functional interface has a single abstract method

@FunctionalInterface
public interface Concatenator {
    
    // Single Abstract Method (SAM)
    public String concatenate(String x, String y);

}
