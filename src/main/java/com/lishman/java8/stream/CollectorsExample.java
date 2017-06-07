package com.lishman.java8.stream;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
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

        List<Team> listOfTeams = teams();
        Stream<Team> teamStream = listOfTeams.stream();

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

        Map<String, List<Team>> teamsByGroup = teams.get()
                .collect(Collectors.groupingBy(Team::getGroup));
        System.out.println("Teams by group: " + teamsByGroup);

        Map<String, List<String>> teamNamesByGroup = teams.get()
                .collect(Collectors.groupingBy(Team::getGroup,
                        Collectors.mapping(Team::getName, Collectors.toList())));
        System.out.println("Team names by group: " + teamNamesByGroup);

        Map<String, Integer> groupGoals = teams.get()
                .collect(Collectors.groupingBy(Team::getGroup,
                         Collectors.summingInt(Team::getGoalsFor)));
        System.out.println("Group goals: " + groupGoals);

        // TODO groupingBy with downstream
        // TODO groupingByConcurrent

        Map<Boolean, List<Team>> teamsWithPositiveGoalDifference = teams.get()
                .collect(Collectors.partitioningBy(t -> t.goalDifference > 0));
        System.out.println("Goal difference (team): " + teamsWithPositiveGoalDifference);

        Map<Boolean, List<String>> teamNamesWithPositiveGoalDifference = teams.get()
                .collect(Collectors.partitioningBy(team -> team.goalDifference > 0,
                        Collectors.mapping(Team::getName, Collectors.toList())));
        System.out.println("Goal difference (name): " + teamNamesWithPositiveGoalDifference);


        /*
            The reducing() collectors are most useful when used in a multi-level reduction,
            downstream of groupingBy or partitioningBy. To perform a simple reduction on a stream,
            use Stream.reduce(BinaryOperator) instead.
         */

        Comparator<Team> byNumber = Comparator.comparing(Team::getPoints);
        Map<String, Optional<Team>> firstResult =  teams.get()
                .collect(Collectors.groupingBy(Team::getGroup, Collectors.reducing(BinaryOperator.maxBy(byNumber))));

        firstResult.forEach( (key, value) -> System.out.printf("Group %s max is %s%n", key, value.map(Team::getName).orElse("not known")) );

        Map<String, Integer> secondResult =  teams.get()
                .collect(Collectors.groupingBy(Team::getGroup, Collectors.reducing(0, Team::getPoints, Integer::sum)));

        secondResult.forEach( (key, value) -> System.out.printf("Group %s total is %s%n", key, value) );

        List<Team> unmodifiableTeams = teams.get()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        System.out.println("Unmodifiable list: " + unmodifiableTeams);

        Collection<Team> teamCollectiion = teams.get().collect(Collectors.toCollection(ArrayList::new));
        List<Team> teamList = teams.get().collect(Collectors.toList());
        Set<Team> teamSet = teams.get().collect(Collectors.toSet());
        Map<String, Team> teamMap = teams.get().collect(Collectors.toMap(Team::getName, Function.identity()));

        // Custom

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
