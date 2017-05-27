package com.lishman.java8;

import java.util.IntSummaryStatistics;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class SupplierExample {

    public static void main(String[] args) {

        Supplier<IntStream> sup = () -> IntStream.range(10, 40);

        IntSummaryStatistics stats = sup
                .get()
                .filter(i -> i%2 ==0)
                .summaryStatistics();

        System.out.println(stats);


        long count = sup.get().count();

        System.out.println(count);



    }

}
