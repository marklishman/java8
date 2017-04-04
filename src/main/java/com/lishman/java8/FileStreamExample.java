package com.lishman.java8;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class FileStreamExample {

    public static void main(String[] args) {
        
        Path path = FileSystems.getDefault().getPath("js", "website.js");
        
        try (Stream<String> lines = Files.lines(path)) {
            Optional<String> line = lines.filter(l -> l.contains("next")).findFirst();
            if (line.isPresent()) {
                System.out.println(line.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
