package com.lishman.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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

        teams().stream().forEach(System.out::println);

    }

    private static List<Team> teams() {

        String fileName = "data/world-cup-2014.csv";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            return stream
                    .filter(row -> ! row.startsWith("Group"))
                    .map(row -> Team.builder().row(row).build())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
