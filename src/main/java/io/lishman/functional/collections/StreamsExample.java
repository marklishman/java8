package io.lishman.functional.collections;

import io.lishman.functional.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsExample {

    public static void main(String[] args) {
        new StreamsExample().streamMethod();
        new StreamsExample().streamsFromArray();
        new StreamsExample().aggregation();
    }

    public void streamMethod() {
        List<Person> people = Person.getPeopleList();

        System.out.println(people.stream().count());
        
        Predicate<Person> pred = (p) -> p.age > 20;
        
        // stream
        people
            .stream()
            .filter(pred)
            .forEach(p -> System.out.println(p.name));

        // parallel stream
        people
            .parallelStream()
            .filter(pred)
            .forEach(p -> System.out.println(p.name));
        
        // another parallel stream
        people
            .stream()
            .parallel()
            .filter(pred)
            .forEach(p -> System.out.println(p.name));
        
        // stream and function reference
        Optional<Person> youngest = people.stream().min(this::comparePersonByAge);
        if (youngest.isPresent()) {
            System.out.println("Youngest: " + youngest.get());
        }

    }
    
    private void streamsFromArray() {
        Person[] people = Person.getPeopleArray();

        // stream from Stream class
        Stream<Person> stream = Stream.of(people);
        
        Optional<Person> first = stream.findFirst();
        if (first.isPresent()) {
            System.out.println(first.get());
        }
        
        // stream from Arrays class
        Arrays.stream(people).skip(1).limit(2).forEach(p -> System.out.println(p));

    }

    private void aggregation() {
        List<Person> people = Person.getPeopleList();

        // sum
        int total = people
            .stream()
            .mapToInt(p -> p.age)
            .sum();
        System.out.println("Age total: " + total);
        
        // average
        OptionalDouble avg = people
                .stream()
                .mapToInt(p -> p.age)
                .average();
        if (avg.isPresent()) {
            System.out.println("Average age: " + avg.getAsDouble());
        }
    }
    
    private int comparePersonByAge(Person p1, Person p2) {
        return new Integer(p1.age).compareTo(p2.age);
    }
}
