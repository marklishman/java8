package com.lishman.java8.stream;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.*;

/**
 *      Whirlwind! tour!
 *
 *      Write for Future Me
 */

public class StreamExample {

    public static void main(String[] args) {


        //~~~~ intro





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

        Stream<Date> timer = Stream.generate(StreamExample::getDateAfterOneSecond);

        Stream<BigDecimal> timerIter = Stream.iterate(BigDecimal.ZERO, bd -> bd.add(BigDecimal.TEN));

        List<Team> teamList = teams();
        Stream<Team> teamStream = teamList.stream();

        Supplier<Stream<Team>> teams = () -> teams().stream();

        Stream<String> moreWords = Arrays.stream("four, five, six".split(","));

        Stream<String> concat = Stream.concat(words, moreWords);

        Stream empty = Stream.empty();


        //~~~~ for each

        longs.forEach(System.out::println);


        //~~~~ map

        teams.get().map(team -> team.name).forEach(System.out::println);


        //~~~~ filter

        integers.filter(i -> i % 2 ==0).forEach(System.out::println);

        teams.get().filter(t -> t.group.equals("C")).forEach(t -> System.out.println(t.name));

        // map and filter together
        teams.get()
                .filter(t -> t.group.equals("C"))
                .map(t -> t.name)
                .forEach(System.out::println);


        //~~~~ find

        Optional<String> anyTeamFromGroupD = teams.get()
                .filter(t -> t.group.equals("D"))
                .map(t -> t.name)
                .findAny();
        System.out.println("Any group D team: " + anyTeamFromGroupD.get());

        Optional<String> firstTeamFromGroupD = teams.get()
                .filter(t -> t.group.equals("D"))
                .map(t -> t.name)
                .findAny();
        System.out.println("First group D team: " + firstTeamFromGroupD.get());

        Optional<String> anyTeamFromGroupZ = teams.get()
                .filter(t -> t.group.equals("Z"))
                .map(t -> t.name)
                .findAny();
        System.out.println("Any group Z team: " + anyTeamFromGroupZ.orElse("none"));


        //~~~~ count, sum etc

        long count = teams.get().count();
        System.out.println("Number of teams: " + count);

        long sum = teams.get().mapToInt(team -> team.goalsFor).sum();
        System.out.println("Total number of goals: " + sum);

        DoubleSummaryStatistics stats = doubles.summaryStatistics();
        System.out.println(stats);


        //~~~~ match

        boolean allMatch = teams.get().allMatch(team -> team.group != null);
        System.out.println("All teams have a group: " + allMatch);

        boolean anyMatch = teams.get().anyMatch(team -> team.drawn == 3);
        System.out.println("At least one country drew all its games? " + anyMatch);


        //~~~~ skip, limit

        timerIter.limit(100).skip(80).forEach(System.out::println);

        timer.limit(5).forEach(System.out::println);


        //~~~~ distinct
        teams.get().map(t -> t.group).distinct().forEach(System.out::println);


        //~~~~ reduce

//        String teamsWithNoPoints = data.get()
//                .filter(team -> team.points == 0)
//                .reduce(StringBuilder::new, (a, b) -> a + ", " + b)

        //~~~~ collect

        // collect(Collector<? super T,A,R> collector)
        // collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)


        // filter with not()

    }

    private static Date getDateAfterOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private static List<Team> teams() {

        String fileName = "data/world-cup-2014.csv";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            return stream
                    .skip(1)
//                    .filter(not(row -> row.startsWith("Group")))
                    .map(row -> Team.builder().teamCsv(row).build())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <R> Predicate<R> not(Predicate<R> predicate) {
        return predicate.negate();
    }

}
