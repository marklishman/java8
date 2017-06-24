package io.lishman.functional.functionalinterface;

import lombok.Builder;

import java.util.Arrays;
import java.util.function.Predicate;

public class CustomFunctionalInterface {

    @FunctionalInterface
    private interface PersonFunction {

        Boolean check(Predicate<Person> predicate);

        default Boolean isAdult() {
            return check(person -> person.age > 18);
        }
    }

    @FunctionalInterface
    private interface PeopleFunction<T> {

        T apply(Person[] people);

        static PeopleFunction<Integer> count() {
            return people -> people.length;
        }

        static PeopleFunction<Double> average() {
            return people -> Arrays.stream(getPeopleArray())
                    .mapToDouble(p -> p.age)
                    .average()
                    .getAsDouble();
        }
    }

    @Builder
    static class Person implements PersonFunction {
        final String name;
        final int age;

        @Override
        public Boolean check(Predicate<Person> predicate) {
            return predicate.test(this);
        }
    }

    public static void main(String[] args) {
        System.out.println("Number of people: " + PeopleFunction.count().apply(getPeopleArray()));
        System.out.println("Average Age: " + PeopleFunction.average().apply(getPeopleArray()));

        Arrays.stream(getPeopleArray()).forEach(
                (p) -> System.out.println(p.name + ": " + p.isAdult())
        );
    }

    private static Person[] getPeopleArray() {
        return new Person[] {
            new Person("Rita", 19),
            new Person("Sue", 21),
            new Person("Bob", 32)
        };
    }
}
