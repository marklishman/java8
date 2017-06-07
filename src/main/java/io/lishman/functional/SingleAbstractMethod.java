package io.lishman.functional;

/**
 * Objects with methods can be stored and passed around,
 * but NOT functions on their own.
 */

public class SingleAbstractMethod {

    interface Calculator {
        // Single Abstract Method (SAM)
        int calculate(int x, int y);
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator() {
            @Override
            public int calculate(int x, int y) {
                return x + y;
            }
        };

        System.out.println("Sum: " + calculator.calculate(10, 20));



        int product = new Calculator() {
            @Override
            public int calculate(int x, int y) {
                return x * y;
            }
        }.calculate(10, 20);

        System.out.println("Product: " + product);



        logger(120, 200, new Calculator() {
            @Override
            public int calculate(int x, int y) {
                return x - y;
            }
        });


    }

    private static void logger(final int x, final int y, final Calculator calculator) {
        System.out.printf("Values: %s and %s, Result: %s%n", x, y, calculator.calculate(x, y));
    }

}
