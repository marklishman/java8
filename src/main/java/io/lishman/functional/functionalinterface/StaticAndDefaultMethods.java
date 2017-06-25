package io.lishman.functional.functionalinterface;

import lombok.Builder;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StaticAndDefaultMethods {

    @FunctionalInterface
    private interface PersonChecker {

        Boolean check(Predicate<Person> predicate);

        default Boolean isAdult() {
            return check(person -> person.age > 18);
        }

        default Boolean startsWith(String prefix) {
            return check(person -> person.name.startsWith(prefix));
        }
    }

    @FunctionalInterface
    private interface PeopleFunction<T> {

        T apply(Person[] people);

        static PeopleFunction<List<Person>> adults() {
            return people -> Arrays.stream(people)
                    .filter(Person::isAdult)
                    .collect(Collectors.toList());
        }

        static PeopleFunction<Integer> count() {
            return people -> people.length;
        }

        static PeopleFunction<Double> average() {
            return people -> Arrays.stream(people)
                    .mapToDouble(p -> p.age)
                    .average()
                    .getAsDouble();
        }
    }

    @Builder
    static class Person implements PersonChecker {
        final String name;
        final int age;

        @Override
        public Boolean check(Predicate<Person> predicate) {
            return predicate.test(this);
        }
    }

    private static Person[] getPeopleArray() {
        return new Person[] {
            new Person("Rita", 19),
            new Person("Sue", 21),
            new Person("Bob", 32)
        };
    }

    public static void main(String[] args) {

        System.out.println("\nAdult beginning with S");
        Arrays.stream(getPeopleArray())
                .filter(PersonChecker::isAdult)
                .filter(person -> person.startsWith("S"))
                .map(p -> p.name)
                .forEach(System.out::println);

        System.out.println("\nIs Bob?");
        Predicate<Person> predicate = (person) -> person.name.equals("Bob");
        Arrays.stream(getPeopleArray())
                .filter(person -> person.check(predicate))
                .map(p -> p.name)
                .forEach(System.out::println);

        System.out.println("\nNumber of people: " + PeopleFunction.count().apply(getPeopleArray()));
        System.out.println("Average Age: " + PeopleFunction.average().apply(getPeopleArray()));
        PeopleFunction.adults().apply(getPeopleArray()).stream().map(p -> p.name).forEach(System.out::println);
    }
}
