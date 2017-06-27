package io.lishman.functional.lambda;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class SimpleLambda {

    @FunctionalInterface
    interface MyFunctionalInterface {
        int MyFunctionalMethod(int value);
    }

    // using an anonymous inner class (to remind us how ugly they are)
    MyFunctionalInterface myFunctionalInterface = new MyFunctionalInterface() {
        @Override
        public int MyFunctionalMethod(int value) {
            return value * value;
        }
    };

    // using a lambda expression
    MyFunctionalInterface myLambda1 = (int value) -> {
        return value * value;
    };

    // we can infer the type
    MyFunctionalInterface myLambda2 = value -> {
        return value * value;
    };

    // and drop 'return' and curly braces for a single statement
    MyFunctionalInterface myLambda3 = value -> value * value;

    // but not for multiple statements
    MyFunctionalInterface myLambda4 = value -> {
        System.out.println("square of " + value);
        return value * value;
    };



    // another example

    Function<String, Integer> function = new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return s.length();
        }
    };

    Function<String, Integer> lambda1 = (String s) -> {
        return s.length();
    };

    Function<String, Integer> lambda2 = s -> {
        return s.length();
    };

    Function<String, Integer> lambda3 = s -> s.length();

    Function<String, Integer> lambda4 = String::length;

    ToIntFunction<String> lambda5 = String::length;
}
