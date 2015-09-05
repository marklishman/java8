package com.lishman.java8.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.lishman.java8.Person;

public class TraverseCollectionExample {
    
    public static void main(String[] args) {
        new TraverseCollectionExample().forEach();
        new TraverseCollectionExample().predicate();
    }
    
    public void forEach() {
        
        List<String> strings = Arrays.asList(new String[]{"aaa", "bbb", "ccc", "ddd", "eee"});
        strings.forEach(s -> System.out.println(s));
        
        List<Person> people = Person.getPeopleList();
        people.forEach(p -> {
            System.out.println(String.format("%s (%s)", p.name, p.age));
        });
        
    }

    public void predicate() {
        
        Predicate<Person> pred = (p) -> p.age > 20;
        
        List<Person> people = Person.getPeopleList();
        people.forEach(p -> {
            if (pred.test(p)) {
                System.out.println(String.format("%s (%s)", p.name, p.age));
            }
        });

    }

}
