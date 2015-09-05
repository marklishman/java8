package com.lishman.java8;

import java.util.Arrays;
import java.util.List;

public class Person {
    
    public String name;
    public int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s)", name, age);
    }

    public static List<Person> getPeopleList() {
        return Arrays.asList(getPeopleArray());
    }

    public static Person[] getPeopleArray() {
        return new Person[] {
                new Person("Rita", 19),
                new Person("Sue", 21),
                new Person("Bob", 32)
        };
    }
    
    
}
