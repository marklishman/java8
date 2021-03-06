package io.lishman.functional;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class JoinerExample {

    public static void main(String[] args) {
        
        String[] a = {"aaa", "bbb", "ccc"};

        String result = String.join("-", a);
        System.out.println(result);
        
        StringJoiner sj1 = new StringJoiner(",", "{", "}");
        sj1.setEmptyValue("empty");
        System.out.println(sj1);
        
        sj1.add("xxx")
            .add("yyy")
            .add("zzz");
        System.out.println(sj1);

        StringJoiner sj2 = new StringJoiner(" and ");
        Person.getPeopleList().forEach(p -> sj2.add(p.name));
        System.out.println(sj2);

        String joined = getPeopleList()
                .stream()
                .map(p -> p.name)
                .collect(Collectors.joining(", "));
        System.out.println(joined);
        
    }

    private static List<Person> getPeopleList() {
        return Arrays.asList(
                new Person("Rita", 19),
                new Person("Sue", 21),
                new Person("Bob", 32));
    }
    
}
