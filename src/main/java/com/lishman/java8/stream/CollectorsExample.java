package com.lishman.java8.stream;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.*;

public class CollectorsExample {

    public static void main(String[] args) {


        //~~~~ types / initializing

        Stream<String> words = Stream.of("one", "two", "three");

        IntStream integers = IntStream.range(25, 50);

        LongStream longs = LongStream.rangeClosed(15, 20);

        DoubleStream.Builder doublesBuilder = DoubleStream.builder();
        doublesBuilder.accept(100);
        doublesBuilder.accept(200);
        doublesBuilder.accept(300);
        DoubleStream doubles = doublesBuilder
                .add(123)
                .add(456)
                .add(789)
                .build();

        Stream<BigDecimal> timerIter = Stream.iterate(BigDecimal.ZERO, bd -> bd.add(BigDecimal.TEN));

        List<Team> teamList = teams();
        Stream<Team> teamStream = teamList.stream();

        Supplier<Stream<Team>> teams = () -> teams().stream();

        Stream<String> moreWords = Arrays.stream("four, five, six".split(","));

        Stream<String> concat = Stream.concat(words, moreWords);

        Stream empty = Stream.empty();


        //~~~~ collect

        /**
         *
         *      counting
         *      summing[Type]
         *      averaging[Type]
         *
         *      minBy
         *      maxBy
         *
         *      summarizing[Type]
         *
         *      joining
         *
         *      groupingBy
         *      groupingByConcurrent
         *
         *      partitioningBy
         *
         *      reducing
         *
         *      mapping
         *      collectingAndThen
         *
         *      toCollection
         *      toList
         *      toSet
         *      toMap
         *      toConcurrentMap
         *
         *      Custom
         */

        System.out.println("Count: " + teams.get().collect(Collectors.counting()));
        System.out.println("Sum: " + teams.get().collect(Collectors.summingInt(team -> team.goalsFor)));
        System.out.println("Average: " + teams.get().collect(Collectors.averagingDouble(team -> team.goalsFor)));

        System.out.println("Team with lowest points: " + teams.get()
                .collect(Collectors.minBy(Comparator.comparingInt((t) -> t.points)))
                .map(team -> team.name)
                .get());
        // maxBy too

        System.out.println("Points stats: " + teams.get()
                .collect(Collectors.summarizingDouble((t -> t.points))));

        String names = teams.get().collect(Collectors.mapping(team -> team.name, Collectors.joining(", ")));
        System.out.println("Team names: " + names);
    }

    private static List<Team> teams() {
        return teamRows()
                .stream()
                .filter(not(row -> row.startsWith("Group")))
                .map(row -> Team.builder().teamCsv(row).build())
                .collect(Collectors.toList());
    }

    private static List<String> teamRows() {
        String fileName = "data/world-cup-2014.csv";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <R> Predicate<R> not(Predicate<R> predicate) {
        return predicate.negate();
    }

}
