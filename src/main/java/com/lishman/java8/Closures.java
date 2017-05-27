package com.lishman.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Closures {

    public static void main(String[] args) {

        String externalString = "xyz";

        Consumer<String> concat = val -> System.out.println(val + externalString);
        concat.accept("abc");

        // Error:(15, 42) java: local variables referenced from a lambda expression must be final or effectively final
//        Consumer<String> change = val -> externalString = "ppp";

        List<Integer> list = new ArrayList<>();
        Consumer<Integer> listFunction = val -> list.add(val);
        listFunction.accept(123);
        System.out.println("List size: " + list.size());

    }
}