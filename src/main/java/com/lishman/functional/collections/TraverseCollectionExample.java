package com.lishman.functional.collections;

import com.lishman.functional.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TraverseCollectionExample {
    
    public static void main(String[] args) {
        new TraverseCollectionExample().forEach();
        new TraverseCollectionExample().predicate();
    }
    
    private void forEach() {
        
        List<String> strings = Arrays.asList(new String[]{"aaa", "bbb", "ccc", "ddd", "eee"});
        strings.forEach(System.out::println);

        Stream.of("aaa", "bbb", "ccc", "ddd", "eee")
                .forEach(System.out::println);

        List<Person> people = Person.getPeopleList();
        people.forEach(p -> System.out.println( String.format("%s (%s)", p.name, p.age)) );

}

    private void predicate() {
        
        Predicate<Person> pred = (p) -> p.age > 20;
        
        List<Person> people = Person.getPeopleList();

        people.forEach(p -> {
            if (pred.test(p)) {
                System.out.println(String.format("%s (%s)", p.name, p.age));
            }
        });

        people.stream()
                .filter(pred)
                .forEach(p ->  System.out.println(String.format("%s (%s)", p.name, p.age)));

    }

}
