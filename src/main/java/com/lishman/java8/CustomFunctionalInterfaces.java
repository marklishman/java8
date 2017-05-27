package com.lishman.java8;

import java.util.Arrays;

public class CustomFunctionalInterfaces {

    @FunctionalInterface
    private interface PeopleFunction<T> {

        T apply(Person[] people);

        static PeopleFunction<Integer> count() {
            return people -> people.length;
        }

        static PeopleFunction<Double> average() {
            return people -> Arrays.stream(people).mapToDouble(p -> p.age).average().getAsDouble();
        }

    }

    public static void main(String[] args) {
        System.out.println("Number of people: " + PeopleFunction.count().apply(getPeopleArray()));
        System.out.println("Average Age: " + PeopleFunction.average().apply(getPeopleArray()));
    }


    static class Person {
        final String name;
        final int age;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    private static Person[] getPeopleArray() {
        return new Person[] {
            new Person("Rita", 19),
            new Person("Sue", 21),
            new Person("Bob", 32)
        };
    }
}
