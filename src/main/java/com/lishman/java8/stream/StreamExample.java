package com.lishman.java8.stream;

import org.apache.commons.lang3.math.NumberUtils;

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


        // TODO quick intro


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

        // order is non-deterministic
        longs.forEach(System.out::println);

        // order guaranteed
        LongStream moreLongs = LongStream.rangeClosed(15, 20);
        moreLongs.forEachOrdered(System.out::println);

        /*
             Some streams, such as a List or Array stream, are intrinsically ordered.
             In other words, they have an encounter order.
             Some intermediate stream operations, such as sorted(), introduce an encounter order.

             forEachOrdered() is guaranteed to return the elements in this encounter order.
             For streams, such as a HashSet, which are not ordered, forEachOrdered() will return elements in any order.

             forEach() does not guarantee the order of the returned elements.
             It is non-deterministic - it may return a different order each time.
             However, it can perform better with parallel operations.

         */


        //~~~~ map

        teams.get().map(team -> team.name).forEach(System.out::println);

        teams.get().mapToDouble(team -> team.points).forEachOrdered(System.out::println);


        //~~~~ filter

        integers.filter(i -> i % 2 ==0).forEach(System.out::println);

        teams.get().filter(t -> t.group.equals("C")).forEach(t -> System.out.println(t.name));

        // map and filter together
        teams.get()
                .filter(t -> t.group.equals("C"))
                .map(t -> t.name)
                .forEach(System.out::println);


        //~~~~ find
        /*
             findFirst() is guaranteed to find the first one in this encounter order.
             For streams, such as a HashSet, which are not ordered, findFirst() will return any element.

             findAny() returns any element.
             It is non-deterministic - it may return different values each time.
             However, it can perform better with parallel operations.

         */

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


        //~~~~ peek

        // mainly used for debugging
        teams.get()
                .filter(t -> t.group.equals("D"))
                .peek(System.out::println)
                .map(t -> t.name)
                .peek(System.out::println)
                .findAny();


        //~~~~ count, sum, average, min, max

        // reduce methods

        long count = teams.get().count();
        System.out.println("Number of teams: " + count);

        long sum = teams.get().mapToInt(team -> team.goalsFor).sum();
        System.out.println("Total number of goals: " + sum);

        OptionalDouble averagePoints = teams.get().mapToDouble(team -> team.points).average();
        System.out.println("Average points: " + averagePoints.orElse(0));

        OptionalInt maxGoalDiff = teams.get().mapToInt(team -> team.goalDifference).max();
        System.out.println("Maximum goal difference: " + maxGoalDiff.orElse(0));

        OptionalInt minGoalDiff = teams.get().mapToInt(team -> team.goalDifference).min();
        System.out.println("Minimum goal difference: " + minGoalDiff.orElse(0));

        DoubleSummaryStatistics stats = doubles.summaryStatistics();
        System.out.println(stats);


        //~~~~ match

        boolean allMatch = teams.get().allMatch(team -> team.group != null);
        System.out.println("All teams have a group: " + allMatch);

        boolean noneMatch = teams.get().noneMatch(team -> team.group.equals("Z"));
        System.out.println("No teams have a group of 'Z': " + noneMatch);

        boolean anyMatch = teams.get().anyMatch(team -> team.drawn == 3);
        System.out.println("At least one country drew all its games? " + anyMatch);


        //~~~~ skip, limit

        timerIter.limit(100).skip(80).forEach(System.out::println);

        timer.limit(5).forEach(System.out::println);


        //~~~~ sorted

        teams.get().map(t -> t.name).sorted().forEach(System.out::println);

        // not the same as
        // teams.get().map(t -> t.name).forEachOrdered(System.out::println);

        teams.get()
                .sorted((t1, t2) -> Integer.compare(t2.points, t1.points))
                .map(t -> t.name)
                .limit(5)
                .forEachOrdered(System.out::println);


        //~~~~ distinct

        teams.get().map(t -> t.group).distinct().forEach(System.out::println);


        //~~~~ flatMap

        String[] groups = new String[] {
                "one,two,three",
                "four,five,six",
                "seven,eight,nine"
        };
        Arrays.stream(groups)
                .flatMap(group -> Arrays.stream(group.split(",")))
                .forEach(System.out::println);

        long negativeCount = teamRows()
                .stream()
                .flatMap(row -> Arrays.stream(row.split(",")))
                .filter(NumberUtils::isCreatable)
                .mapToInt(NumberUtils::createInteger)
                .filter(item -> item < 0)
                .count();
        System.out.println("Negative count: " + negativeCount);

        double[][] numberGroups = {
                {3,6,2},
                {1.4,2.2},
                {10,8,6,4,2}
        };
        Arrays.stream(numberGroups)
                .flatMapToDouble(group -> Arrays.stream(group))
                .forEachOrdered(System.out::println);


        //~~~~ to array

        Object[] teamsAsArray = teams.get().toArray();

        String[] groupHNames = teams.get()
                .filter(t -> t.group.equals("H"))
                .map(t -> t.name)
                .toArray(String[]::new);
        System.out.println("Group H teams: " + Arrays.toString(groupHNames));


        //~~~~ reduce

        int goalsScored = teams.get()
                .map(team -> team.goalsFor)
                .reduce(( total, goalsFor) -> total + goalsFor)
                .orElse(0);
        System.out.println("Goals scored: " + goalsScored);

        teams.get()
                .map(team -> team.goalsFor)
                .reduce(0, (total, goalsFor) -> total + goalsFor);

        teams.get().reduce(0, (total, team) -> total + team.goalsFor, Integer::sum);


        //~~~~ collect

        String teamsWithNoPoints = teams.get()
                .filter(team -> team.points == 0)
                .map(team -> team.name)
                .collect(StringBuilder::new,
                        (sb, name) -> sb.append(" ").append(name),
                        (sb1, sb2) -> sb1.append(sb2))
                .toString();
        System.out.println("Teams with no points: " + teamsWithNoPoints);

        List<String> teamNameList = teams.get()
                .filter(t -> t.group.equals("A"))
                .map(t -> t.name)
                .collect(ArrayList<String>::new,
                        (list, name) -> list.add(name),
                        (list1, list2) -> list1.addAll(list2));
        System.out.println("Group A team names: " + teamNameList);


        // collect(Collector<? super T,A,R> collector)

        /*
            reduce() vs collect()

            Both terminal operations, reduce() and collect(), are categorized as reduction operations.

            In collect() operations, elements are incorporated by updating the state of a mutable
            container object.

            In reduce() operations result is updated by replacing the previous result.
        */

        // TODO collectors




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
