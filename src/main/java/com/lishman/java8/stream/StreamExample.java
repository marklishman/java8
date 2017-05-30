package com.lishman.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Stream
 *
 *      IntStream
 *      LongStream
 *      DoubleStream
 *
 */

public class StreamExample {

    public static void main(String[] args) {
        Supplier<Stream> data = () -> teams().stream();

        data.get().forEach(System.out::println);

        long count = data.get().count();
        System.out.println("Count: " + count);
    }

    private static <R> Predicate<R> not(Predicate<R> predicate) {
        return predicate.negate();
    }

    private static List<Team> teams() {

        String fileName = "data/world-cup-2014.csv";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            return stream
                    .filter(not(row -> row.startsWith("Group")))
                    .map(row -> Team.builder().teamCsv(row).build())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
