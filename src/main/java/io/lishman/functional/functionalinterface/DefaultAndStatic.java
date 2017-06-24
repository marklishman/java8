package io.lishman.functional.functionalinterface;

public class DefaultAndStatic {

    @FunctionalInterface
    public interface Calculator {

        double calculate(int a, int b);

        static Calculator add() {
            return (a, b) -> (double) a + b;
        }

        static Calculator subtract() {
            return (a, b) -> (double) a - b;
        }

        static Calculator multiply() {
            return (a, b) -> (double) a * b;
        }

        static Calculator divide() {
            return (a, b) -> (double) a / b;
        }


    }

    public static void main(String[] args) {

        System.out.println(Calculator.add().calculate(101, 345));
        System.out.println(Calculator.subtract().calculate(678, 55));
        System.out.println(Calculator.multiply().calculate(12, 17));
        System.out.println(Calculator.divide().calculate(144, 10));

    }

}