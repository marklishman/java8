package io.lishman.functional.stream;

import io.lishman.functional.Person;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.*;


public class StreamExamples {

    public static void main(String[] args) {

        List<Person> people = Person.getPeopleList();

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


        //~~~~ forEach

        // order is non-deterministic
        longs.forEach(System.out::println);

        // order guaranteed
        LongStream moreLongs = LongStream.rangeClosed(15, 20);
        moreLongs.forEachOrdered(System.out::println);


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
