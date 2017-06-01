package com.lishman.java8.stream;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.*;

/**
 *      Whirlwind! tour!
 */

public class StreamExample {

    public static void main(String[] args) {

        //~~~~ types / initializing

        Stream<String> words = Stream.of("one", "two", "three");

        IntStream integers = IntStream.range(5, 10);

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

        Stream<Date> timer = Stream.generate(() -> getDateAfterOneSecond());

        Stream<BigDecimal> timerIter = Stream.iterate(BigDecimal.ZERO, bd -> bd.add(BigDecimal.TEN));

        List<Team> teamList = teams();
        Stream<Team> teams = teamList.stream();

        Stream<String> moreWords = Arrays.stream("four, five, six".split(","));





        timerIter.limit(100).skip(80).forEach(System.out::println);

        timer.limit (5).forEach(System.out::println);




        Supplier<Stream<Team>> data = () -> teams().stream();

        //~~~~ for each

        data.get().forEach(System.out::println);


        //~~~~ match

        boolean allMatch = data.get().allMatch(team -> team.group != null);
        System.out.println("All teams have a group: " + allMatch);

        boolean anyMatch = data.get().anyMatch(team -> team.drawn == 3);
        System.out.println("At least one country drew all its games? " + anyMatch);


        //~~~~ count, sum etc

        long count = data.get().count();
        System.out.println("Number of teams: " + count);

        long sum = data.get().mapToInt(team -> team.goalsFor).sum();
        System.out.println("Total number of goals: " + sum);

        // Summary statistics


        //~~~~ reduce

//        String teamsWithNoPoints = data.get()
//                .filter(team -> team.points == 0)
//                .reduce(StringBuilder::new, (a, b) -> a + ", " + b)



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
