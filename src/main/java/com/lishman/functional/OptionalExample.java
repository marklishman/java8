package com.lishman.functional;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OptionalExample {

    /*

        of
        ofNullable

        filter
        map
        flatMap

        isPresent
        ifPresent

        get

        orElse
        orElseGet
        orElseThrow

        Optional[Type]

     */

    public static void main(String[] args) {

        Optional<String> optString = Optional.of("abc");
        Optional<String> emptyString = Optional.empty();

        System.out.println("filter: " + optString.filter( v -> v.startsWith("a")).get());
        System.out.println("map: " + optString.map(String::toUpperCase).get());
        System.out.println("flatMap: " + optString.flatMap((s) -> Optional.of(s + "xyz")).get());

        // ifPresent() combines isPresent() & get()
        if (optString.isPresent()) {
            System.out.println(optString.get());
        }
        // becomes
        optString.ifPresent(System.out::println);

        System.out.println("orElse: " + emptyString.orElse("empty"));
        System.out.println("orElseGet: " + emptyString.orElseGet(() -> "nothing".toUpperCase()));
        try {
            emptyString.orElseThrow(RuntimeException::new);
        } catch (RuntimeException e) {
            System.out.println("error");
        }

        // with streams
        IntStream.range(1, 10)
                .filter(i -> i > 5)
                .findAny()
                .ifPresent(System.out::println);

        System.out.println(Stream.of("one", "two", "three")
                .filter(s -> s.startsWith("x"))
                .findAny()
                .filter(s -> s.endsWith("y"))
                .map(String::toUpperCase)
                .orElse("not found")
        );

    }

}

