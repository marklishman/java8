package io.lishman.functional.function;

import java.util.Comparator;

public class FunctionalInterfaceExample {

    Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return Integer.compare(a, b);
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("running!");
        }
    };

    @FunctionalInterface
    interface MyFunctionalInterface {
        int MyFunctionalMethod(int value);
    }

    MyFunctionalInterface myFunctionalInterface = new MyFunctionalInterface() {
        @Override
        public int MyFunctionalMethod(int value) {
            return value * value;
        }
    };

}